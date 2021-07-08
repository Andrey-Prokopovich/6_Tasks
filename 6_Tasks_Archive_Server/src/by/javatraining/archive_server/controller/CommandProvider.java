package by.javatraining.archive_server.controller;

import java.util.HashMap;
import java.util.Map;

import by.javatraining.archive_server.constant.ClientServer;
import by.javatraining.archive_server.controller.implementation.AddNewFileCommand;
import by.javatraining.archive_server.controller.implementation.AddUserCommand;
import by.javatraining.archive_server.controller.implementation.ChangeInfoCommand;
import by.javatraining.archive_server.controller.implementation.GetFullInfoCommand;
import by.javatraining.archive_server.controller.implementation.UnknownCommand;
import by.javatraining.archive_server.controller.implementation.VerifyUserCommand;

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

public class CommandProvider {

	private Map<String, Command> commands = new HashMap<>();
	private final Command unknownCommand;

	public CommandProvider() {
		unknownCommand = new UnknownCommand();

		commands.put(ClientServer.addUser, new AddUserCommand());
		commands.put(ClientServer.verifyUser, new VerifyUserCommand());
		commands.put(ClientServer.getFullInfo, new GetFullInfoCommand());
		commands.put(ClientServer.changeInfo, new ChangeInfoCommand());
		commands.put(ClientServer.addNewFile, new AddNewFileCommand());
	}

	public Command getCommand(String commandName) {
		Command result = commands.get(commandName);

		if (result == null) {
			result = unknownCommand;
		}

		return result;
	}
}
