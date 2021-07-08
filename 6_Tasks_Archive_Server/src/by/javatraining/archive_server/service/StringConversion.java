package by.javatraining.archive_server.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class StringConversion {

	private static final int defaultToNumResult = 0;

	public StringConversion() {
	}

	public String intToStr(int value) {
		return String.valueOf(value);
	}

	public int strToInt(String line) {
		if (isStrToIntPossible(line))
			return Integer.parseInt(line);
		else
			return defaultToNumResult;
	}

	public boolean isStrToIntPossible(String line) {
		try {
			Integer.parseInt(line);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public String longToStr(long value) {
		return String.valueOf(value);
	}

	public Long strToLong(String line) {
		if (isStrToLongPossible(line))
			return Long.parseLong(line);
		else
			return (long) defaultToNumResult;
	}

	public boolean isStrToLongPossible(String line) {
		try {
			Long.parseLong(line);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public String dateToStr(Date date) {
		return new SimpleDateFormat("dd.MM.yyyy").format(date);
	}

	public Date strToDate(String line) {
		try {
			return new SimpleDateFormat("dd.MM.yyyy").parse(line);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String byteArrayToHexStr(byte[] byteArray) {
		StringBuilder strBuilder = new StringBuilder();

		for (byte b : byteArray) {
			strBuilder.append(String.format("%02X", b));
		}

		return strBuilder.toString();
	}

	public byte[] hexStrToByteArray(String s) {
		int len = s.length();
		byte[] byteArray = new byte[len / 2];

		for (int i = 0; i < len; i += 2) {
			byteArray[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}

		return byteArray;
	}
}
