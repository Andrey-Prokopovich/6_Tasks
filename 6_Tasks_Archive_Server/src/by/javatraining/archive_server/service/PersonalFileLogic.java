package by.javatraining.archive_server.service;

import by.javatraining.archive_server.dao.DAOException;
import by.javatraining.archive_server.dao.DAOProvider;
import by.javatraining.archive_server.dao.PersonalFileDAO;
import by.javatraining.archive_server.entity.PersonalFile;
import by.javatraining.archive_server.entity.PersonalFileFull;

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

public class PersonalFileLogic {

	public PersonalFileLogic() {
	}
	
	public long getNextNumber() {
		PersonalFileDAO personalFileDAO = DAOProvider.getInstance().getPersonalFileDAO();
		
		return personalFileDAO.getNextNumber();
	}
	
	public PersonalFile[] getFilesList() throws ServiceException {
		PersonalFileDAO personalFileDAO = DAOProvider.getInstance().getPersonalFileDAO();
		
		try {
			return personalFileDAO.getFilesList();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void writeFileData(PersonalFileFull pFile) throws ServiceException {
		PersonalFileDAO personalFileDAO = DAOProvider.getInstance().getPersonalFileDAO();

		try {
			personalFileDAO.writeFileData(pFile);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public PersonalFile readFileData(PersonalFile pFile) throws ServiceException {
		PersonalFileDAO personalFileDAO = DAOProvider.getInstance().getPersonalFileDAO();

		try {
			return personalFileDAO.readFileData(pFile);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public PersonalFileFull readFileData(PersonalFileFull pFile) throws ServiceException {
		PersonalFileDAO personalFileDAO = DAOProvider.getInstance().getPersonalFileDAO();

		try {
			return personalFileDAO.readFileData(pFile);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
