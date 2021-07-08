package by.javatraining.archive_server.entity;

import java.io.Serializable;

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

public class User implements Serializable {

	private static final long serialVersionUID = 514907509853688636L;

	private String login;
	private boolean allowedChange;
	private boolean allowedAddition;

	public User(String login, boolean allowedChange, boolean allowedAddition) {
		this.login = login;
		this.allowedChange = allowedChange;
		this.allowedAddition = allowedAddition;
	}

	public String getLogin() {
		return login;
	}

	public boolean isAllowedChange() {
		return allowedChange;
	}

	public boolean isAllowedAddition() {
		return allowedAddition;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setAllowedChange(boolean allowedChange) {
		this.allowedChange = allowedChange;
	}

	public void setAllowedAddition(boolean allowedAddition) {
		this.allowedAddition = allowedAddition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (allowedAddition ? 1231 : 1237);
		result = prime * result + (allowedChange ? 1231 : 1237);
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (allowedAddition != other.allowedAddition) {
			return false;
		}
		if (allowedChange != other.allowedChange) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", allowedChange=" + allowedChange + ", allowedAddition=" + allowedAddition
				+ "]";
	}
}
