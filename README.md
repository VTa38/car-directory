# car-directory

Я писал код на Java с использованием Spring framwork. 

Поддерживаемое API:
•	GET /cars/ - выводит весь список машин
	В случае если записей не было список будет пуст

•	GET /cars/{id} - выводит машину, с заданным id
  Если нет машины с заданным id выйдет ошибка

•	GET /cars/number/{number} - выводит машину, с заданным регистрационным номером
  Если нет машины с заданным регистрационным номером выйдет ошибка
  
  •	GET /cars/brand/{brand} - выводит список машин, с заданной маркой
  Если машин с заданной маркой нет список будет пустым
  
  •	GET /cars/color/{color} - выводит список машин, с заданным цветом
  Если машин с заданным цветом нет список будет пустым

•	POST /cars/ - принимает Json, на основе чего добавляет новую машину в базу данных. Пример Json запроса на добавление машины:
    {"number":"A888AA09","brand":"Rеno","color":"Black"}. (значения не могут быть пустыми)
	В случае если машина уже есть в БД выйдет ошибка, существование определяется по регистрационным номерам в БД.

•	DELETE /cars/{id} – удаляет существующую машину по переданному id
	В случае если машины с заданным id не было выйдет ошибка

•	PUT /cars/{id} – обновляет информацию о существующей машине по переданному Json. Примеры Json запросов на обновление машины:
    {"number":"A888AA09",color":"Black"}, {"number":"A888AA09"}, {color":"Black"}. (значения могут быть пустыми)
  В случае если указанной записи не было выйдет ошибка. Изменить можно номера (если такие номера уже есть в БД выйдет ошибка) и цвет. Марку сменить нельзя

•	GET /statistics – выводит статистику по справочнику: количество машин 
                                                            самая популярная марка
                                                            самый популярный цвет
                                                            дата первой записи
                                                            дата последнего обновления

•	GET /statistics/brands – выводит все уникальные марки автомобилей из списка

•	GET /statistics/colors – выводит все уникальные цвета автомобилей из списка


О работе приложения:
Записи о машинах хранятся в БД. В таблице cars записи, описывающие состояние машины: id, number, brand, color, date. Получение запроса происходит в классах CarController и StatisticsController, которые находятся в пакете controllers. CarController поддерживает стандартные CRUD операции над таблицей cars, а также выводить список машин отильтрованный по цвету или марке. StatisticsController выводит статистику по списку машин, а также список уникальных цветов и марок. 
![image](https://user-images.githubusercontent.com/67002782/177033572-930f30ec-d422-4ada-ad3a-ffb7f67cd70d.png)


CarController только вызывает соответствующий метод из CarService, в котором реализована логика формирования ответа на запрос.
CarController:
![image](https://user-images.githubusercontent.com/67002782/177033669-18acc57c-9d1c-4b9f-822e-1e80e23e36fb.png)
![image](https://user-images.githubusercontent.com/67002782/177033684-780d5f47-ee07-4755-ad80-3e5ef1060553.png)


Ошибки, которые могут возникать в запросах, выводятся ExceptionController, который находится в пакете controller. В конструктор ошибки передаётся константные сообщение об ошибке и статус ошибки (NOT_FOUND или BAD_REQUEST)
![image](https://user-images.githubusercontent.com/67002782/177033813-d5dac87d-0eac-448d-a745-58fc56836e87.png)


Работа происходит с двумя моделями Car и Statistics
Car, основная модель, с ней постоянно происходит работа. 
![image](https://user-images.githubusercontent.com/67002782/177033838-1af53279-0d0e-485c-8b87-20af51838585.png)
Car состоит из 5 параметров(id, number, brand, color, timestamp) и 2 конструкторов(конструктор по умолчанию и конструктор с переменными: number, brand и color). Стандартные асессоры(Getter и Setter). Важный нюанс, явно задать дату нельзя, дата задаётся автоматически в момент создания экземпляра Car или обновляется с обновлением записи о машине. 
Переопределённые методы hashCode() и equals() в классе Car.
![image](https://user-images.githubusercontent.com/67002782/177033867-5b56cadc-72ca-49a6-8434-2cbe0b391544.png)

CarRepository интерфейс для работы с таблицей cars 
![image](https://user-images.githubusercontent.com/67002782/177033880-caacb30c-f465-494e-b626-5f560423663f.png)


Statistics, которая выдаётся только при запросе /statistics.
![image](https://user-images.githubusercontent.com/67002782/176862690-ba80aaba-cf36-4fab-ba7b-1b8e6aff43be.png)
Статистика выдаётся по требованию и не кэшируется, поэтому при каждом запросе статистики, статистика будет содержать актуальные данные.
Статистику по записям и список уникальных марок и цветов собирает сервис StatisticsService:
![image](https://user-images.githubusercontent.com/67002782/177033928-23ca3ee2-ba8b-43f8-92ad-dfe6837f5a7f.png)
В нём все алгоритмы поиска необходимых данных, при этом он ничего не хранит и не кэширует, он просто собирает актуальные данные.


POST запрос добавление новой машины
![image](https://user-images.githubusercontent.com/67002782/177034204-aeeb5a94-bdb1-4574-b521-9ebe31c758e1.png)

GET запросы получение списка всех машин
![image](https://user-images.githubusercontent.com/67002782/177034218-34d1560e-c591-4f47-be2a-79d686ee5dce.png)

GET запрос на получение машины по id
![image](https://user-images.githubusercontent.com/67002782/177034243-858c1946-c48b-498d-8585-41e91936fb1e.png)

GET запрос на получение машины по регистрационному номеру
![image](https://user-images.githubusercontent.com/67002782/177034260-b83a25ef-5f07-4427-8049-1df76d53d961.png)

PUT запрос на изменение записи
![image](https://user-images.githubusercontent.com/67002782/177034280-348371c7-ab9b-4918-90d4-75709dcbedbc.png)

DELETE запрос на удаление записи
![image](https://user-images.githubusercontent.com/67002782/177034297-a05ac40c-c64c-4d7a-879f-dab03109c8c8.png)

GET запрос получение статистики
![image](https://user-images.githubusercontent.com/67002782/177034306-eb2f66d4-666d-4345-9eb8-cd63b1425aab.png)

GET запрос получение уникальных цветов
![image](https://user-images.githubusercontent.com/67002782/177034362-d7683d1a-a2a9-48ae-a90e-a0909c02d48d.png)

GET запрос получение уникальных марок
![image](https://user-images.githubusercontent.com/67002782/177034368-ab58deb4-41a6-4b20-98d4-6107987a667e.png)

