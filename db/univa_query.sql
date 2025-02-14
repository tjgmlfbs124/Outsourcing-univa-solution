/* 질문 상태 테이블 생성*/
CREATE TABLE IF NOT EXISTS `univa`.`question_state` (
  `id` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB

/* 질문 상태 테이블 내용 추가 */
INSERT 
	INTO question_state(`name`)
	VALUES ("결제 대기"),("풀이 대기"),
		("풀이중"),("답변 완료"),
		("리뷰 완료"),("이의 제기 신청"),
		("환불 신청"),("환불 완료");

/* 관리자(직원) 테이블 생성 TODO: 임시 테이블 입니다 */
CREATE TABLE IF NOT EXISTS `univa`.`manager` (
  `id` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB

/* 질문 테이블 생성 */
CREATE TABLE IF NOT EXISTS `univa`.`question` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `visible` INT UNSIGNED NOT NULL DEFAULT 1,
  `title` VARCHAR(255) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `content` TEXT NULL,
  `upload_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `limit_date` DATETIME NULL,
  `manager_id` INT UNSIGNED NULL,
  `state` INT UNSIGNED NOT NULL DEFAULT 1,
  `score` INT UNSIGNED NULL,
  `review` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_question_manager1_idx` (`manager_id` ASC) VISIBLE,
  INDEX `fk_question_question_state1_idx` (`state` ASC) VISIBLE,
  CONSTRAINT `fk_question_manager1`
    FOREIGN KEY (`manager_id`)
    REFERENCES `univa`.`manager` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_question_question_state1`
    FOREIGN KEY (`state`)
    REFERENCES `univa`.`question_state` (`id`)
    ON DELETE SET DEFAULT
    ON UPDATE CASCADE )
ENGINE = InnoDB

/* question.score defualt 값 변경*/
ALTER TABLE question MODIFY COLUMN `score` INT UNSIGNED NOT NULL DEFAULT 0;

/* 질문 테이블 제약조건 수정 */
ALTER TABLE `question`
DROP CONSTRAINT `fk_question_question_state1`;

ALTER TABLE `question`
ADD CONSTRAINT `fk_question_question_state2`
FOREIGN KEY (`state`) REFERENCES `question_state`(`id`)
ON DELETE NO ACTION
ON UPDATE CASCADE;

/* 질문내 문제 테이블 생성*/
CREATE TABLE IF NOT EXISTS `univa`.`problem` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `question_id` INT UNSIGNED NOT NULL,
  `number` INT NOT NULL,
  `text` TEXT NULL,
  `image_url` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_problem_question1_idx` (`question_id` ASC) VISIBLE,
  CONSTRAINT `fk_problem_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `univa`.`question` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE )
ENGINE = InnoDB

/* 문제 테이블 타입 변경 */
ALTER TABLE problem
MODIFY COLUMN `number` int unsigned NOT NULL;


/* 매니저에 컬럼 추가 */
ALTER TABLE `manager`
ADD `username` VARCHAR(45) NOT NULL;
ALTER TABLE `manager`
ADD `password` VARCHAR(45) NOT NULL;

/* 답변 테이블 생성 */
CREATE TABLE IF NOT EXISTS `univa`.`answer` (
  `id` INT UNSIGNED NOT NULL,
  `question_id` INT UNSIGNED NOT NULL,
  `content` TEXT NULL,
  `answer_date` DATETIME NULL,
  INDEX `fk_answer_question1_idx` (`question_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_answer_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `univa`.`question` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB

/* 매니저 칼럼 ON DELTE, ON UPDATE 수정*/
ALTER TABLE `answer`
DROP FOREIGN KEY `fk_answer_question1`;

ALTER TABLE `answer`
ADD CONSTRAINT `fk_answer_question2`
FOREIGN KEY(`question_id`) REFERENCES `question`(`id`)
ON DELETE NO ACTION
ON UPDATE CASCADE;

/* 문제 답변 테이블 생성 */
CREATE TABLE IF NOT EXISTS `univa`.`problem_answer` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `answer_id` INT UNSIGNED NOT NULL,
  `number` INT NOT NULL,
  `text` TEXT NULL,
  `image_url` VARCHAR(255) NULL,
  INDEX `fk_problem_answer_answer1_idx` (`answer_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_problem_answer_answer1`
    FOREIGN KEY (`answer_id`)
    REFERENCES `univa`.`answer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB

/* 답변 테이블 id(pk) 타입 변경 */
ALTER TABLE `problem_answer` drop foreign key `fk_problem_answer_answer1`;

ALTER TABLE `answer`
MODIFY `id` INT UNSIGNED AUTO_INCREMENT NOT NULL;

ALTER TABLE `problem_answer`
ADD CONSTRAINT `fk_problem_answer_answer2`
FOREIGN KEY(`answer_id`) REFERENCES `answer`(`id`)
ON DELETE CASCADE
ON UPDATE CASCADE;

/* 매니저 테이블 id(pk) 타입 변경 */
ALTER TABLE `question` DROP FOREIGN KEY `fk_question_manager1`;

ALTER TABLE `manager`
MODIFY `id` INT UNSIGNED AUTO_INCREMENT NOT NULL;

ALTER TABLE `question` 
ADD CONSTRAINT `fk_question_manager2`
FOREIGN KEY(`manager_id`) REFERENCES `manager`(`id`)
ON DELETE SET NULL
ON UPDATE CASCADE;

/* 메니저 테이블 username(로그인 아이디) 유니크화 */
ALTER TABLE `univa`.`manager` 
ADD UNIQUE INDEX `username_UNIQUE` (`username`);

/* 엔서 테이블 answer_date 를 자동으로 생성되도록 변경 */
ALTER TABLE `univa`.`answer`
MODIFY `answer_date` datetime DEFAULT CURRENT_TIMESTAMP; 

/* 주제 테이블 생성 */
CREATE TABLE IF NOT EXISTS `univa`.`subject` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB

/* 질문주제 연결 테이블 생성 */
CREATE TABLE IF NOT EXISTS `univa`.`question_subject` (
  `subject_id` INT UNSIGNED NOT NULL,
  `question_id` INT UNSIGNED NOT NULL,
  INDEX `fk_question_subject_subject1_idx` (`subject_id` ASC) VISIBLE,
  INDEX `fk_question_subject_question1_idx` (`question_id` ASC) VISIBLE,
  CONSTRAINT `fk_question_subject_subject1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `univa`.`subject` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_question_subject_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `univa`.`question` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB

/* 다대다 중간 테이블 pk컬럼 생성 */
ALTER TABLE `univa`.`question_subject` 
ADD COLUMN `id` INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`id`);

/* 채팅 테이블 생성*/
CREATE TABLE IF NOT EXISTS `univa`.`question_chat` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `question_id` INT UNSIGNED NOT NULL,
  `writer` INT NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `image_url` VARCHAR(255) NULL,
  `is_read` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_question_chat_question1_idx` (`question_id` ASC) VISIBLE,
  CONSTRAINT `fk_question_chat_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `univa`.`question` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB

/* 유저 테이블 생성 */
CREATE TABLE IF NOT EXISTS `univa`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NOT NULL,
  `type` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB

/* 질문 테이블 nickname, password 제거 */
ALTER TABLE `question`
DROP `nickname`;
ALTER TABLE `question`
DROP `password`;

/* 질문 테이블에 유저 id 추가 */
ALTER TABLE `question`
ADD `user_id` INT UNSIGNED DEFAULT 0;

/* 질문 테이블 제약조건 추가*/
ALTER TABLE `question`
ADD CONSTRAINT `fk_question_user1`
FOREIGN KEY(`user_id`) REFERENCES `univa`.`user`(`id`)
ON DELETE SET DEFAULT
ON UPDATE CASCADE;

/* 질문_채팅 테이블 type 컬럼 추가 */
ALTER TABLE `question_chat`
ADD `type` VARCHAR(45) NOT NULL;

/* 상위 카테고리 제약 설정 */
ALTER TABLE `subject`
ADD `parent_id` int unsigned NULL;

ALTER TABLE `subject` 
ADD CONSTRAINT `fk_subject_parent1`
FOREIGN KEY(`parent_id`) REFERENCES `subject`(`id`)
ON DELETE SET NULL
ON UPDATE CASCADE;

/* 질문 컬럼 추가 */
ALTER TABLE `question`
ADD (
  `isNew` INT NULL DEFAULT 0,
  `isDetail` INT NULL DEFAULT 0,
  `language` VARCHAR(45) NULL
)