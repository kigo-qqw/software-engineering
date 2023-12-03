## Лабораторная работа 2. 
### Рефлексия. 
Фабрика на основе аннотированных констант. Класс содержит аннотированные константы вида
```java
public final static int ModBusPriorityLevels=4;
@CONST(group = "Priority", title = "Interrupt", clazz = A.class)
public final static int MBPInterrupt = 0;
@CONST(group = "Priority", title = "High", clazz = B.class)
public final static int MBPHigh = 1;
```
Каждая константа имеет свое имя, название (title), группу, значение и присоединенный класс. Получив класс с аннотированными константами, компонента вернуть ArrayList описателей констант (имя, название (title), значение и присоединенный класс). Тест получает от класса ArrayList описателей, создает в GUI выпадающий список названий и при выборе элемента создает соответствующий объект класса.

![Example](./assets/example.gif)