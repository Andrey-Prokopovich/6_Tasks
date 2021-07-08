package by.javatraining.archive_server.dao.implementation;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import by.javatraining.archive_server.constant.GloConst;
import by.javatraining.archive_server.dao.DAOException;
import by.javatraining.archive_server.dao.UserDAO;
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

public class FileUserDAO implements UserDAO {

	private String pathToAuthData;

	public FileUserDAO() {
		findFileAuthData();
	}

	private void findFileAuthData() {
		Path path = FileSystems.getDefault().getPath(GloConst.fileNameAuthData);
		pathToAuthData = path.toAbsolutePath().toString();
	}

	public boolean noUsersExist() {
		File file = new File(pathToAuthData);

		return file.length() == 0;
	}

	public void addUser(String name, String password, boolean allowChange, boolean allowAdd) throws DAOException {
		StringConversion stringConversion = ServiceProvider.getInstance().getStringConversion();

		try (FileOutputStream outputStream = new FileOutputStream(pathToAuthData, true)) {
			byte[] salt = Encryption.generateSalt();
			byte[] ecrtptedPassword = Encryption.getEncryptedPassword(password, salt);
			salt = stringConversion.byteArrayToHexStr(salt).getBytes();

			outputStream.write((name + "|").getBytes());
			outputStream.write(ecrtptedPassword);
			outputStream.write("|".getBytes());
			outputStream.write(salt);
			outputStream.write(("|" + allowChange).getBytes());
			outputStream.write(("|" + allowAdd + "\n").getBytes());
		} catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new DAOException(e);
		}
	}

	public boolean[] searchForUser(String name, String password, boolean checkExistance) throws DAOException {
		String line = "";
		String[] words;
		boolean[] result = { false, false, false };
		StringConversion stringConversion = ServiceProvider.getInstance().getStringConversion();

		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(pathToAuthData)))) {
			while ((line = bufferedReader.readLine()) != null) {
				if (checkExistance) {
					if (line.startsWith(name + "|")) {
						result[0] = true;
						break;
					}
				} else {
					while (!(line.endsWith("true") || line.endsWith("false"))) {
						line += bufferedReader.readLine();
					}

					words = line.split("\\|");
					if (words.length < 5)
						continue;

					byte[] salt = stringConversion.hexStrToByteArray(words[2]);
					String chkPassword = new String(Encryption.getEncryptedPassword(password, salt),
							StandardCharsets.UTF_8);
					if (words[0].equals(name) && words[1].equals(chkPassword)) {
						result[0] = true;
						result[1] = words[3].equals("true");
						result[2] = words[4].equals("true");
						break;
					}
				}
			}
		} catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new DAOException(e);
		}

		return result;
	}
}
