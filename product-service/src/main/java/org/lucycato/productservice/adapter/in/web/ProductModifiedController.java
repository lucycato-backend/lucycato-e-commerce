package org.lucycato.productservice.adapter.in.web;


import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.annotation.resolver.AdminUserHeaders;
import org.lucycato.common.annotation.resolver.AppUserHeaders;
import org.lucycato.common.resolver.AdminUserHeaderDetail;
import org.lucycato.productservice.domain.LectureSeries;
import org.lucycato.productservice.domain.Teacher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class ProductModifiedController {

    //TODO: product-service update
    //TODO: safe delete
    //Teacher
    //LectureSeries
    //Lecture
    //LectureContent
    //LectureTextEBook
    //TeacherNews
    //LectureReviews

}
