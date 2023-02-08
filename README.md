# TarkovHelpApplication

Это приложение сделано для Геймеров, чтобы улучшить их игровой опыт и ускорить поиск нужных материалов связанных с игрой Escape From Tarkov.

В качестве фронта использован API телеграмма. Все взаимодействие с приложением выполнено через телеграмм-бота. 

Вы можете ввести следующие команды из главного меню :
  /start - чтобы увидеть приветствие 
  /find quest_name - чтобы найти нужный квест 
  /quests - чтобы увидеть список торговцев и далее найти нужный квест 
  /help - чтобы снова увидеть это сообщение
  
На стороне бэка реализован парсинг сайта tarkov.help с помощью библиотеки jsoup.
Написаны все необходимые модели и связи для сохранения результатов парсинга и работы с БД Postgres.
При инициализации приложения выполняется парсинг всех необходимых материалов. 
Запросы из телеграмма работают через сервисный слой далее с БД.

Также реализован тестовый функционал рассылки сообщений пользователям бота. Сохранения данных мо пользователях, различные кнопки и плюшки.
В дальнейшем планируется расширить функционал приложения. 

Приложение и БД разворачивается локально. При необходимости можно использовать Docker для разворачивания приложения на серваке.
