package by.javatraining.archive_server.controller.implementation;

import by.javatraining.archive_server.constant.ClientServer;
import by.javatraining.archive_server.controller.Command;
import by.javatraining.archive_server.entity.User;
import by.javatraining.archive_server.service.ServiceException;
import by.javatraining.archive_server.service.ServiceProvider;
import by.javatraining.archive_server.service.UserLogic;

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

public class AddUserCommand implements Command {

	@Override
	public boolean needAdditionalParams() {
		return true;
	}

	@Override
	public Object[] execute(User user, Object object) {
		UserLogic userLogic = ServiceProvider.getInstance().getUserLogic();

		try {
			String word = (String) object;
			String[] words = word.split(":", 4);

			if (userLogic.searchForUser(words[0], words[1], true)[0]) {
				return new Object[] { ClientServer.errorMessage, "User with this name alredy exists." };
			}

			userLogic.addUser(words[0], words[1], words[2].equals("true"), words[3].equals("true"));

			return new Object[] { ClientServer.normalResult };
		} catch (ServiceException e) {
			return new Object[] { ClientServer.errorMessage, e.getMessage() };
		}
	}
}
