package by.javatraining.archive_server.controller.implementation;

import by.javatraining.archive_server.constant.ClientServer;
import by.javatraining.archive_server.controller.Command;
import by.javatraining.archive_server.entity.PersonalFileFull;
import by.javatraining.archive_server.entity.User;
import by.javatraining.archive_server.service.PersonalFileLogic;
import by.javatraining.archive_server.service.ServiceException;
import by.javatraining.archive_server.service.ServiceProvider;

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

public class ChangeInfoCommand implements Command {

	@Override
	public boolean needAdditionalParams() {
		return true;
	}

	@Override
	public Object[] execute(User user, Object object) {
		PersonalFileLogic personalFileLogic = ServiceProvider.getInstance().getPersonalFileLogic();

		if (user == null) {
			return new Object[] { ClientServer.errorMessage, "Log in first." };
		}

		if (!user.isAllowedChange()) {
			return new Object[] { ClientServer.errorMessage, "This user not allowed to make chages." };
		}

		try {
			PersonalFileFull pFile = (PersonalFileFull) object;

			personalFileLogic.writeFileData(pFile);

			return new Object[] { ClientServer.normalResult };
		} catch (ServiceException e) {
			return new Object[] { ClientServer.errorMessage, e.getMessage() };
		}
	}
}
