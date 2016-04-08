package marius.mac.com.raandroid_header.presenters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import marius.mac.com.raandroid_header.R;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

/**
 * Created by marius on 06/04/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class RestaurantPresenterTest {
    private RestaurantPresenter presenter;
    @Mock
    private RestaurantView view;

    @Before
    public void setUp() throws Exception {
        presenter = new RestaurantPresenter(view);
    }

    @Test
    public void testSearchByPostCodeWhenEmptyText() throws Exception {
        when(view.getSearchViewValue()).thenReturn("");
        presenter.onSearchButtonClicked();
        verify(view).showSearchEmptyErrorText(R.string.search_empty_text_error);
    }

    @Test
    public void testSearchByPostCodeWIthValidEntry() throws Exception {
        when(view.getSearchViewValue()).thenReturn("S10");
        presenter.onSearchButtonClicked();
        wait(2000);
        assertTrue(presenter.getAdapterListSize() > 0);

    }
}