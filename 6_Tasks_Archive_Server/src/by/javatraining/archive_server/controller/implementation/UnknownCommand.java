package by.javatraining.archive_server.controller.implementation;

import by.javatraining.archive_server.constant.ClientServer;
import by.javatraining.archive_server.controller.Command;
import by.javatraining.archive_server.entity.User;

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

public class UnknownCommand implements Command {

	@Override
	public boolean needAdditionalParams() {
		return false;
	}

	@Override
	public Object[] execute(User user, Object object) {
		return new Object[] { ClientServer.errorMessage, "Unknown client request." };
	}
}
