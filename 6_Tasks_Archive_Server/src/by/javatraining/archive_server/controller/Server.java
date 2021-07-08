package by.javatraining.archive_server.controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import by.javatraining.archive_server.entity.ServerThread;

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

public class Server {
	public static LinkedList<ServerThread> clientList = new LinkedList<>();

	public Server() {
		try {
			socketCommunication();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void socketCommunication() throws IOException {
		ServerSocket server = new ServerSocket(4004);

		System.out.println("Server started.");

		try {
			while (true) {
				Socket clientSocket = server.accept();

				try {
					clientList.add(new ServerThread(clientSocket));
				} catch (IOException e) {
					clientSocket.close();
				}
			}
		} finally {
			if (!server.isClosed()) {
				server.close();
			}
			System.out.println("Server stoped.");
		}
	}
}
