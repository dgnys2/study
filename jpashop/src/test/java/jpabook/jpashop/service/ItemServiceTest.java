package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

public class ItemServiceTest {

    @Autowired ItemService itemService;
    @Autowired ItemRepository itemRepository;

    @Test
    public void 아이템저장() throws Exception{

        //given
        Book book = new Book();
        book.setName("JPA Book1");
        book.setPrice(10000);
        book.setAuthor("daniel");
        book.setIsbn("123123123");

        //when

        itemService.saveItem(book);

        //then
        Book readBook = (Book) itemRepository.findAll().get(0);

        assertEquals(readBook,book);
        assertEquals(readBook.getName(),book.getName());
        assertEquals(readBook.getAuthor(),book.getAuthor());
        assertEquals(readBook.getPrice(),book.getPrice());
        assertEquals(readBook.getIsbn(),book.getIsbn());


    }

}