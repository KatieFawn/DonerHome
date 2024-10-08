package com.jiromo5.donerhome;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class) // Устанавливаем наш стартер.
public class TemplateTest {

    @Mock
    Object yourDependency; //Наша зависимость, которую использует сервис.

    @InjectMocks
    Object yourService; //Внедряем в наш сервис зависимость.

    /**
     * Инициализация наших моков.
     */

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Наш тестовый метод.
     */

    @Test
    public void templateTest(){
        //1. Подготовка тестовых данных.
        //2. Настраиваем моки на необходимое поведение.
        // Mockito.when(yourDependency.someMethod).thenReturn( Метод должен вернуть конкретные данные (1) );


        //3. Выполняем тестируемый метод.
        //yourService.someMethod();

        // Assert проверка результатов.
        // Mockito.verify(yourDependency).someMethod;

        // Ещё можно проверить состояние методов, вызвав assertEquals.

    }

    @Test
    public void templateTestError(){
        //Тот же самый код, как и в обычных тестах, но с добавлением методов на тестирование исключений.

        // Тестируем вызов метода на выбрасывание ошибки.
        // when(yourService.someMethod()).thenReturn(new Exception());

        // Если метод не возвращает значение.
        // doThrow(new RuntimeException("Ошибка сети")).when(authFormPutRequest).sendRequest(any());

        // Проверяем на выбрасывание конкретного исключения
        // Exception exception = assertThrows(RuntimeException.class, () -> {
        //            authFormPutRequest.sendRequest(singleEmitter); // singleEmitter должен быть определен
        //        });

        // Проверяем сообщение исключения
        //assertEquals("Ошибка сети", exception.getMessage());
    }

}
