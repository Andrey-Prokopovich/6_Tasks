package by.javatraining.archive_server.service;

import by.javatraining.archive_server.dao.DAOException;
import by.javatraining.archive_server.dao.DAOProvider;
import by.javatraining.archive_server.dao.UserDAO;

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

public class UserLogic {

	public UserLogic() {
	}

	public boolean noUsersExist() {
		UserDAO userDAO = DAOProvider.getInstance().getUserDAO();

		return userDAO.noUsersExist();
	}

	public void addUser(String name, String password, boolean allowChange, boolean allowAdd) throws ServiceException {
		UserDAO userDAO = DAOProvider.getInstance().getUserDAO();

		try {
			userDAO.addUser(name, password, allowChange, allowAdd);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public boolean[] searchForUser(String name, String password, boolean checkExistance) throws ServiceException {
		UserDAO userDAO = DAOProvider.getInstance().getUserDAO();

		try {
			return userDAO.searchForUser(name, password, checkExistance);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
