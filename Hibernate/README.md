[![Build Status](https://travis-ci.org/alekseyponkin/aponkin.svg?branch=dev)](https://travis-ci.org/alekseyponkin/aponkin)
[![codecov](https://codecov.io/gh/alekseyponkin/aponkin/branch/dev/graph/badge.svg)](https://codecov.io/gh/alekseyponkin/aponkin)
# TODO list 

## Задание: необходимо создать простое приложение TODO list.
1. Одна таблица в базе данных.
1. Веб приложение должно иметь одну страницу index.html. 
1. На странице форма: добавить новое задание, описание.
1. Ниже список всех заданий и галочка: выполнено / не выполнено.
1. Вверху списка галочка: "показать все". Если включена, то показывать все задания. Если нет - только невыполненные.
1. Все данные на форму загружаются через AJAX.
1. Данные должны сохраняться через Hibernate.

## Применямые технологии
Tomcat Servlet Container, Hibernate, PostgreSQL, Jackson, JUnit, Mockito, HTML, Bootstrap, jQuery .

## Изображения работы
Добавление нового задания 
    ![Добавление нового задания](src/main/webapp/images/Add_task.jpg)
    
Отоброжаение не выполненых заданий 
    ![Отоброжаение не выполненых заданий](src/main/webapp/images/Show_not_done_tasks.jpg)
    
Отоброжаение всех заданий 
    ![Отображение всех заданий](src/main/webapp/images/Show_all_tasks.jpg)
    
Удаление задачи и обновление статуса задачи 
    ![Удаление и обновление задачи](src/main/webapp/images/Delete_and_update_task.jpg)
