package com.vanhieu.api.web;

import com.vanhieu.dto.ItemDto;
import com.vanhieu.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class WebpageApi {

    @Autowired
    private IItemService itemService;

    @GetMapping("/WEBPAGE/category")
    public String showPages(Model model, HttpServletRequest request ) {
        Pageable pageable = PageRequest.of(0, 20);
        Long id = Long.valueOf(request.getParameter("categoryId"));
        List<ItemDto> items = itemService.findByCategoryId(id, pageable);
        StringBuilder html = new StringBuilder("<div class=\"row featured__filter\" id=\"product\" >\n");
        for (ItemDto item : items) {
            String s = "<div class=\"col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat\">\n" +
                    "       <div class=\"featured__item\">\n" +
                    "           <div class=\"featured__item__pic set-bg\">\n" +
                    "               <img src=\"/web/img/product/" + item.getCategoryCode() + "/" + item.getImage() + ".jpg\" alt=\"\">\n" +
                    "                   <ul class=\"featured__item__pic__hover\">\n" +
                    "                       <li><a href=\"#\"><i class=\"fa fa-heart\"></i></a></li>\n" +
                    "                       <li><a href=\"#\"><i class=\"fa fa-retweet\"></i></a></li>\n" +
                    "                       <li><a href=\"/WEBPAGE/shopDetails/" + item.getId() + "\"><i class=\"fa fa-shopping-cart\"></i></a></li>\n" +
                    "                   </ul>\n" +
                    "            </div>" +
                    "           <div class=\"featured__item__text\">\n" +
                    "               <a href=\"/WEBPAGE/shopDetails/" + item.getId() + "\">\n" +
                    "                   <h6>"+ item.getName() +"</h6>\n" +
                    "                   <h5>"+ item.getPrice() +" $</h5>\n" +
                    "                   <h5 style=\"color:forestgreen;\">see details</h5>\n" +
                    "               </a>" +
                    "           </div>\n" +
                    "       </div>\n" +
                    "    </div>";
            html.append(s);
        }
        html.append("</div>");
        return html.toString();
    }
}
