INSERT INTO app_user VALUES ('TEACHER', 1, 'teacher', 'teacher', 'password'),
                            ('STUDENT', 2, 'student', 'student', 'password'),
                            ('STUDENT', 3, 'second_student', 'second_student', 'password'),
                            ('TEACHER', 4, 'admin', 'admin', 'password');

INSERT INTO textbook VALUES (1, 'textbook_1', true, 'Textbook title', 1),
                            (2, 'textbook_2', false, 'Private containerId', 1);

INSERT INTO textbook_allowed_users VALUES (2, 2);

INSERT INTO chapter VALUES (1, 'chapter 1', 'First chapter'),
                           (2, 'chapter 2', 'Second chapter'),
                           (3, 'chapter 3', 'Third chapter');

INSERT INTO textbook_chapter VALUES (1, 1), (1, 2),
                                    (2, 1), (2, 3);

INSERT INTO chooseaword_content VALUES (1, 'question_1', '{{a}, {x}}', '{1, null, 2, null}', '{{a, b, c}, {x, y, z}}'),
                                       (2, 'question_2', '{{1}}', '{0, null, 2}', '{{1,2,3}}'),
                                       (3, 'question_3', '{{q}, {s}}', '{null, wea, null, d}', '{{q,s}, {q,s}}');

INSERT INTO exercise_content VALUES (1, 'exercise_1', 'Exercise 1', 'choose_a_word'),
                                    (2, 'exercise_2', 'Exercixe 2', 'choose_a_word');

INSERT INTO exercise_content_questions VALUES (1, 1), (1, 2),
                                              (2, 2), (2, 3);

INSERT INTO question_answer VALUES (1, 'answer_for_question_1_by_2', {})
