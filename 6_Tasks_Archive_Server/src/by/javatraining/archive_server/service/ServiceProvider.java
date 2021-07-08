package by.javatraining.archive_server.service;

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

public class ServiceProvider {

	private static final ServiceProvider instance = new ServiceProvider();

	private UserLogic userLogic = new UserLogic();
	private PersonalFileLogic personalFileLogic = new PersonalFileLogic();
	private StringConversion stringConversion = new StringConversion();

	private ServiceProvider() {
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	public UserLogic getUserLogic() {
		return userLogic;
	}

	public PersonalFileLogic getPersonalFileLogic() {
		return personalFileLogic;
	}

	public StringConversion getStringConversion() {
		return stringConversion;
	}
}
