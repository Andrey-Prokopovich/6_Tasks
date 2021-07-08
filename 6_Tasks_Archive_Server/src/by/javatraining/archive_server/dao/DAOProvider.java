package by.javatraining.archive_server.dao;

import by.javatraining.archive_server.dao.implementation.FileUserDAO;
import by.javatraining.archive_server.dao.implementation.XmlPersonalFileDAO;

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

public final class DAOProvider {

	private static final DAOProvider instance = new DAOProvider();

	private UserDAO userDAO = new FileUserDAO();
	private PersonalFileDAO personalFileDAO = new XmlPersonalFileDAO();

	private DAOProvider() {
	}

	public static DAOProvider getInstance() {
		return instance;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public PersonalFileDAO getPersonalFileDAO() {
		return personalFileDAO;
	}
}
