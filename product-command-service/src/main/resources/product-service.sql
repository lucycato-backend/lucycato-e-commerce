mysql -u root -p -h localhost
GRANT ALL PRIVILEGES ON *.* TO `admin`@`%` WITH GRANT OPTION;
FLUSH PRIVILEGES;
SHOW GRANTS FOR `admin`@`%`

CREATE DATABASE IF NOT EXISTS product_service
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `product_service`.`teachers` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `teacher_rank` INT NOT NULL,
    `teacher_name` VARCHAR(50) NOT NULL,
    `teacher_slogan` VARCHAR(100) NOT NULL,
    `teacher_profile_description` VARCHAR(1024) NOT NULL,
    `teacher_image_url` VARCHAR(200) NOT NULL,
    `teacher_curriculum_image_url` VARCHAR(200) NOT NULL,
    `teacher_curriculum_video_url` VARCHAR(200) NOT NULL,
    `teaching_genre` VARCHAR(50) NOT NULL,
    `teacher_status` VARCHAR(50) NOT NULL,
    `teacher_created_at` DATETIME,
    `teacher_modified_at` DATETIME,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `product_service`.`course_series` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `teacher_id` BIGINT,
    `course_series_image_url` VARCHAR(200) NOT NULL,
    `course_series_title` VARCHAR(100) NOT NULL,
    `course_series_description` VARCHAR(512) NOT NULL,
    `course_series_explain_image_urls_json` JSON NOT NULL,
    `subject_category` VARCHAR(50) NOT NULL,
    `course_series_category` VARCHAR(50) NOT NULL,
    `course_series_status` VARCHAR(50) NOT NULL,
    `course_series_created_at` DATETIME,
    `course_series_modified_at` DATETIME,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`teacher_id`) REFERENCES `product_service`.`teachers`(`id`)
        ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `product_service`.`courses` (
    `id`  BIGINT NOT NULL AUTO_INCREMENT,
    `teacher_id` BIGINT,
    `course_series_id` BIGINT,
    `course_title` VARCHAR(100) NOT NULL,
    `course_sub_title` VARCHAR(100) NOT NULL,
    `course_price` INT NOT NULL,
    `course_image_url` VARCHAR(200) NOT NULL,
    `course_description` VARCHAR(1024) NOT NULL,
    `course_genre` VARCHAR(50) NOT NULL,
    `subject_category` VARCHAR(50) NOT NULL,
    `course_status` VARCHAR(50) NOT NULL,
    `course_expired_at` DATETIME,
    `course_created_at` DATETIME,
    `course_modified_at` DATETIME,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`teacher_id`) REFERENCES `product_service`.`teachers` (`id`)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (`course_series_id`) REFERENCES `product_service`.`course_series` (`id`)
        ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `product_service`.`lectures` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `course_id` BIGINT
    `lecture_title` VARCHAR(100) NOT NULL,
    `lecture_description` VARCHAR(512) NOT NULL,
    `lecture_thumbnail_image_url` VARCHAR(200) NOT NULL,
    `lecture_video_url` VARCHAR(200) NOT NULL,
    `lecture_category` VARCHAR(50) NOT NULL,
    `lecture_status` VARCHAR(50) NOT NULL,
    `lecture_created_at` DATETIME,
    `lecture_modified_at` DATETIME,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`course_id`) REFERENCES `product_service`.`courses` (`id`)
        ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `product_service`.`text_e_books` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `course_id` BIGINT,
    `text_e_book_unique_code` VARCHAR(200) NOT NULL,
    `text_e_book_image_url` VARCHAR(200) NOT NULL,
    `text_e_book_title` VARCHAR(100) NOT NULL,
    `text_e_book_description` VARCHAR(512) NOT NULL,
    `text_e_book_table_of_contents` VARCHAR(512) NOT NULL,
    `text_e_book_author` VARCHAR(50) NOT NULL,
    `text_e_book_publisher` VARCHAR(50) NOT NULL,
    `text_e_book_preview_download_url` VARCHAR(200) NOT NULL,
    `text_e_book_full_download_url` VARCHAR(200) NOT NULL,
    `text_e_book_page` INT NOT NULL,
    `subject_category` VARCHAR(50) NOT NULL,
    `text_e_book_status` VARCHAR(50) NOT NULL,
    `text_e_book_published_at` DATETIME,
    `text_e_book_created_at` DATETIME,
    `text_e_book_modified_at` DATETIME,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`course_id`) REFERENCES `product_service`.`courses` (`id`)
        ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB;

