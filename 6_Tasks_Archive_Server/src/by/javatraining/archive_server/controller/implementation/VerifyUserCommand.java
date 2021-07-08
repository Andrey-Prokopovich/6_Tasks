package by.javatraining.archive_server.controller.implementation;

import by.javatraining.archive_server.constant.ClientServer;
import by.javatraining.archive_server.controller.Command;
import by.javatraining.archive_server.entity.PersonalFile;
import by.javatraining.archive_server.entity.User;
import by.javatraining.archive_server.service.PersonalFileLogic;
import by.javatraining.archive_server.service.ServiceException;
import by.javatraining.archive_server.service.ServiceProvider;
import by.javatraining.archive_server.service.StringConversion;
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

public class VerifyUserCommand implements Command {

	@Override
	public boolean needAdditionalParams() {
		return true;
	}

	@Override
	public Object[] execute(User user, Object object) {
		ServiceProvider serviceProvider;
		UserLogic userLogic;
		PersonalFileLogic personalFileLogic;
		StringConversion stringConversion;

		serviceProvider = ServiceProvider.getInstance();
		userLogic = serviceProvider.getUserLogic();
		personalFileLogic = serviceProvider.getPersonalFileLogic();
		stringConversion = serviceProvider.getStringConversion();

		try {
			String word = (String) object;
			String[] words = word.split(":", 2);

			boolean[] result = userLogic.searchForUser(words[0], words[1], false);

			if (result[0]) {
				user = new User(words[0], result[1], result[2]);

				int size = 4; // 2 - результаты авторизации, 2 - количество файлов и конец передачи
				Object[] returnObj;

				PersonalFile[] filesList = personalFileLogic.getFilesList();

				size = size + filesList.length;
				returnObj = new Object[size];

				returnObj[0] = ClientServer.normalResult;
				returnObj[1] = result[0] + ":" + result[1] + ":" + result[2];
				returnObj[2] = stringConversion.intToStr(filesList.length);

				int index = 3;
				for (PersonalFile pFile : filesList) {
					returnObj[index] = pFile;
					index++;
				}

				returnObj[index] = ClientServer.normalResult;

				return returnObj;
			}

			return new Object[] { ClientServer.normalResult, result[0] + ":" + result[1] + ":" + result[2] };
		} catch (ServiceException e) {
			return new Object[] { ClientServer.errorMessage, e.getMessage() };
		}
	}
}
