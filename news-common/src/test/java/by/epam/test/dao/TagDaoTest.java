package by.epam.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

import by.epam.newsmanagement.dao.interfaces.ITagDao;
import by.epam.newsmanagement.entity.Tag;
import by.epam.newsmanagement.exception.dao.DaoException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {by.epam.test.config.SpringTestConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
  DbUnitTestExecutionListener.class })
//@DbUnitConfiguration(databaseConnection="UnitTestPU")
public class TagDaoTest {

  @Autowired
  ITagDao tagDao;

  @Test
  @DatabaseSetup("classpath:dbtests/tagDataset.xml")
  @ExpectedDatabase("classpath:dbtests/tagExpected.xml")
  public void shouldFindTagById(int tagId) throws DaoException {
    Tag tag = null;
    tag = tagDao.getTagById(tagId);
  }

}
