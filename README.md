[![Build status](https://ci.appveyor.com/api/projects/status/mb6u6bwtdvma3grj?svg=true)](https://ci.appveyor.com/project/yoursalex/homeworkaqaakita)

## *Домашнее задание №6.*

### В ходе выполнения домашнего задания были выполнены следующие задачи: 

* Созданы необходимые PageObjects по количеству страниц приложения
* Методы располагаются в тех PageObjects, на которых они затем будут реализованы
* 4 теста проходят корректно:
    1. Проверяем возможность перевода на карту 1 с карты 2 (балансы уменьшаются и увеличиваются соответственно)
    1. Проверяем возможность перевода на карту 2 с карты 1 (балансы уменьшаются и увеличиваются соответственно) 
    1. Проверяем, что с каждой из страниц перевода можно выйти при клике на кнопку Отмена, при этом баланс не изменится. Реализовано 2 теста, по одному для каждой карты.
* Не проходят тесты, демонстрирующие баги: 
    1. Демонстрируется возможность уйти в минус на карте, с которой осуществляется перевод. Так быть не должно
    1. Демонстрируется, что после нажатия кнопки Отмена при возврате на страницу перевода, поля не очищаются автоматически
    1. Демонстрируется, что при вводе для перевода того же номера карты, на которую осуществляется перевод, не появляется сообщение об ошибке


