package by.javatraining.archive_server.controller.implementation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import by.javatraining.archive_server.constant.ClientServer;
import by.javatraining.archive_server.controller.Command;
import by.javatraining.archive_server.controller.CommandProvider;
import by.javatraining.archive_server.controller.Controller;
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

public class ServerController implements Controller {

	private final CommandProvider commandProvider;

	private User user;

	public ServerController() {
		commandProvider = new CommandProvider();
		user = null;
	}

	@Override
	public void start(Socket socket) {
		ObjectInputStream in;
		ObjectOutputStream out;

		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());

			while (true) {
				String word = (String) in.readObject();

				if (word.equals(ClientServer.closeConncetion)) {
					if (!socket.isClosed()) {
						socket.close();
					}
					if (in != null) {
						in.close();
					}
					if (out != null) {
						out.close();
					}

					break;
				}

				Command currentCommand = commandProvider.getCommand(word);

				Object[] response;
				if (currentCommand.needAdditionalParams()) {
					response = currentCommand.execute(user, in.readObject());
				} else {
					response = currentCommand.execute(user, null);
				}

				int size = response.length;
				for (int index = 0; index < size; index++) {
					out.writeObject(response[index]);
					out.flush();
				}
			}
		} catch (ClassNotFoundException | IOException e) {
		}
	}
}
