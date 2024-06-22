package org.lucycato.orderqueryservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PointController {
    @GetMapping("api/app/v1/points")
    public void getUserPoint() {

    }

    @GetMapping("api/app/v1/point-histories")
    public void getUserPointHistories() {

    }

    @GetMapping("api/app/v1/point-histories/{pointHistoryId}")
    public void getUserPointHistory(@PathVariable Long pointHistoryId) {

    }

    @GetMapping("api/admin/v1/point-histories")
    public void getPointHistories() {

    }

    @GetMapping("api/admin/v1/point-histories/{pointHistoryId}")
    public void getPointHistories(@PathVariable Long pointHistoryId) {

    }
}
