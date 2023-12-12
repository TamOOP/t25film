package com.huce.t25film.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ShowDateResourceSort {
    public static List<ShowDateResource> sortShowtimeDateItems(List<ShowDateResource> showtimeDateItems) {
        Collections.sort(showtimeDateItems, new Comparator<ShowDateResource>() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");

            @Override
            public int compare(ShowDateResource item1, ShowDateResource item2) {
                try {
                    Date date1 = dateFormat.parse(item1.getDate());
                    Date date2 = dateFormat.parse(item2.getDate());

                    // Sắp xếp từ bé đến lớn
                    return date1.compareTo(date2);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
        return showtimeDateItems;
    }
}
