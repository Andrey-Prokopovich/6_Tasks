package by.javatraining.archive_server.entity;

import java.io.*;
import java.net.Socket;

import by.javatraining.archive_server.controller.Controller;
import by.javatraining.archive_server.controller.implementation.ServerController;

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

public class ServerThread extends Thread {
	private Socket clientSocket;

	public ServerThread(Socket socket) throws IOException {
		this.clientSocket = socket;

		start();
	}

	@Override
	public void run() {
		Controller controller = new ServerController();

		controller.start(clientSocket);
	}
}
