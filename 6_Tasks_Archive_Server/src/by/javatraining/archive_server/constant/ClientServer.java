package by.javatraining.archive_server.constant;

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

public final class ClientServer {
	public static final String addUser = "addUser";
	public static final String closeConncetion = "endThread";
	public static final String verifyUser = "verifyUser";
	public static final String getFullInfo = "getFullInfo";
	public static final String changeInfo = "changeInfo";
	public static final String addNewFile = "addNewFile";
	
	public static final String normalResult = "normal";
	public static final String errorMessage = "error";
}
