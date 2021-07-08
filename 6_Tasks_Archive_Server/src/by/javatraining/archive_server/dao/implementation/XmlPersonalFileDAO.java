package by.javatraining.archive_server.dao.implementation;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.javatraining.archive_server.constant.GloConst;
import by.javatraining.archive_server.dao.DAOException;
import by.javatraining.archive_server.dao.PersonalFileDAO;
import by.javatraining.archive_server.entity.PersonalFile;
import by.javatraining.archive_server.entity.PersonalFileFull;
import by.javatraining.archive_server.service.ServiceProvider;
import by.javatraining.archive_server.service.StringConversion;

/*
 * Попробуйте решить данную задачу хотя бы на 50%.
 * 
 * Создайте клиент-серверное приложение "Архив".
 * 
 * Общие требования к заданию:
 * * В архиве хранятся Дела(например, студентов). Архив находится на сервере.
 * * Клиент, в зависимости от прав, может запросить дело на просмотр,
 *   внести в него изменения, или создать новое дело.
 *   
 * Требования к коду лабораторной работы:
 * * Для реализации сетевого соединения используйте сокеты.
 * * Формат хранения данных на сервере - xml-файлы.
 */

public class XmlPersonalFileDAO implements PersonalFileDAO {

	private String pathToXML;

	public XmlPersonalFileDAO() {
		findPathToXML();
	}

	private void findPathToXML() {
		Path path = FileSystems.getDefault().getPath("");
		pathToXML = path.toAbsolutePath().toString() + GloConst.filePathToXML;
	}

	private String getFilePath(Long fileNumber) {
		StringConversion stringConversion = ServiceProvider.getInstance().getStringConversion();

		return pathToXML + stringConversion.longToStr(fileNumber) + ".xml";
	}

	public long getNextNumber() {
		File dir;
		String[] files;
		StringConversion stringConversion;

		dir = new File(pathToXML);
		files = dir.list();
		stringConversion = ServiceProvider.getInstance().getStringConversion();

		for (int i = 0; i < files.length; i++) {
			files[i] = files[i].replaceAll(".xml", "");
		}

		Arrays.sort(files, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return Long.valueOf(s1).compareTo(Long.valueOf(s2));
			}
		});

		return stringConversion.strToLong(files[files.length - 1]) + 1;
	}

	public PersonalFile[] getFilesList() throws DAOException {
		File dir;
		File[] files;
		StringConversion stringConversion;

		dir = new File(pathToXML);
		files = dir.listFiles();
		stringConversion = ServiceProvider.getInstance().getStringConversion();

		PersonalFile[] filesList = new PersonalFile[files.length];
		for (int i = 0; i < files.length; i++) {
			PersonalFile file = new PersonalFile(stringConversion.strToLong(files[i].getName().replaceAll(".xml", "")));
			filesList[i] = readFileData(file);
		}

		return filesList;
	}

	public void writeFileData(PersonalFileFull pFile) throws DAOException {
		StringConversion stringConversion = ServiceProvider.getInstance().getStringConversion();

		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();

			// root
			Element relFile = document.createElement("personal_file");
			document.appendChild(relFile);

			// children
			Element elFileNumber = document.createElement("file_number");
			elFileNumber.setTextContent(stringConversion.longToStr(pFile.getFileNumber()));
			relFile.appendChild(elFileNumber);

			Element elSurName = document.createElement("surname");
			elSurName.setTextContent(pFile.getSurName());
			relFile.appendChild(elSurName);

			Element elFirstName = document.createElement("firstname");
			elFirstName.setTextContent(pFile.getFirstName());
			relFile.appendChild(elFirstName);

			Element elPatronymic = document.createElement("patronymic");
			elPatronymic.setTextContent(pFile.getPatronymic());
			relFile.appendChild(elPatronymic);

			Element elDateOfBirth = document.createElement("birth_date");
			elDateOfBirth.setTextContent(stringConversion.dateToStr(pFile.getDateOfBirth()));
			relFile.appendChild(elDateOfBirth);

			Element elDateOfEmployment = document.createElement("employment_date");
			elDateOfEmployment.setTextContent(stringConversion.dateToStr(pFile.getDateOfEmployment()));
			relFile.appendChild(elDateOfEmployment);

			Element elDateOfDismissal = document.createElement("dismissal_date");
			elDateOfDismissal.setTextContent(stringConversion.dateToStr(pFile.getDateOfDismissal()));
			relFile.appendChild(elDateOfDismissal);

			Element elSalary = document.createElement("salary");
			elSalary.setTextContent(stringConversion.intToStr(pFile.getSalary()));
			relFile.appendChild(elSalary);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(getFilePath(pFile.getFileNumber())));

			transformer.transform(domSource, streamResult);
		} catch (ParserConfigurationException | TransformerException e) {
			throw new DAOException(e);
		}
	}

	public PersonalFile readFileData(PersonalFile pFile) throws DAOException {
		StringConversion stringConversion = ServiceProvider.getInstance().getStringConversion();

		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = documentBuilder.parse(getFilePath(pFile.getFileNumber()));

			Node root = document.getDocumentElement();
			NodeList nodeList = root.getChildNodes();

			String item;
			item = nodeList.item(0).getTextContent();
			pFile.setFileNumber(stringConversion.strToLong(item));

			item = nodeList.item(1).getTextContent();
			pFile.setSurName(item);

			item = nodeList.item(2).getTextContent();
			pFile.setFirstName(item);

			item = nodeList.item(3).getTextContent();
			pFile.setPatronymic(item);

			return pFile;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new DAOException(e);
		}
	}

	public PersonalFileFull readFileData(PersonalFileFull pFile) throws DAOException {
		StringConversion stringConversion = ServiceProvider.getInstance().getStringConversion();

		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = documentBuilder.parse(getFilePath(pFile.getFileNumber()));

			Node root = document.getDocumentElement();
			NodeList nodeList = root.getChildNodes();

			String item;
			item = nodeList.item(0).getTextContent();
			pFile.setFileNumber(stringConversion.strToLong(item));

			item = nodeList.item(1).getTextContent();
			pFile.setSurName(item);

			item = nodeList.item(2).getTextContent();
			pFile.setFirstName(item);

			item = nodeList.item(3).getTextContent();
			pFile.setPatronymic(item);

			item = nodeList.item(4).getTextContent();
			pFile.setDateOfBirth(stringConversion.strToDate(item));

			item = nodeList.item(5).getTextContent();
			pFile.setDateOfEmployment(stringConversion.strToDate(item));

			item = nodeList.item(6).getTextContent();
			pFile.setDateOfDismissal(stringConversion.strToDate(item));

			item = nodeList.item(7).getTextContent();
			pFile.setSalary(stringConversion.strToInt(item));

			return pFile;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new DAOException(e);
		}
	}
}
