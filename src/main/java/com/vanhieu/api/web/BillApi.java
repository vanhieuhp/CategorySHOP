package com.vanhieu.api.web;

import com.vanhieu.dto.BillDto;
import com.vanhieu.dto.UserDto;
import com.vanhieu.entity.BillEntity;
import com.vanhieu.service.IBillService;
import com.vanhieu.service.ICartService;
import com.vanhieu.service.IUserService;
import com.vanhieu.util.ViewModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillApi {

    @Autowired
    private IBillService billService;

    @Autowired
    private ICartService cartService;

    @PostMapping("/api/bill")
    public BillDto createBill(@RequestBody BillDto billDto) {
        if (ViewModelUtils.getUser() != null) {
            billDto.setUserId(ViewModelUtils.getUser().getId());
            billDto = billService.save(billDto);
            cartService.payments(ViewModelUtils.getUser().getId());
        }
        return billDto;
    }

    @DeleteMapping("/api/bill")
    public void deleteBill(@RequestBody Long[] ids) {
        billService.delete(ids);
    }
}
