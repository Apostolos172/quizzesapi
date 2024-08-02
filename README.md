# Quizzes Api

Σύστημα για τη δημιουργία test-quiz, τον καθορισμό
συμμετεχόντων, τη διεξαγωγή τους και τη διαχείριση των
αποτελεσμάτων.

## App

Μπορείτε να αποκτήσετε πρόσβαση στην εφαρμογή 
και να πειραματιστείτε με αυτή στην παρακάτω διεύθυνση: [localhost:8080/quizzesapi](http://localhost:8080/quizzesapi/)

## Σύντομη περιγραφή δυνατοτήτων του API

### 1. Δυνατότητα καθορισμού τράπεζας ερωτημάτων

1. Προσθήκη ερωτήσεων
    1. Προσθήκη ερωτήσεων σωστού λάθους και των απαντήσεών
       τους [/quizzesapi/trueFalseQuestionsWithAnswers/question](http://localhost:8080/quizzesapi/swagger-ui/index.html#/true-false-questions-controller/saveTrueFalseQuestionAndAnswers)
    2. Προσθήκη ερωτήσεων συμπλήρωσης
       κενών [/quizzesapi/gapFillingQuestionsWithAnswers/question](http://localhost:8080/quizzesapi/swagger-ui/index.html#/gap-filling-questions-controller/saveGapFillingQuestionAndAnswers)
    3. Προσθήκη ερωτήσεων πολλαπλής επιλογής (1 ή περισσότερες σωστές
       απαντήσεις) [/quizzesapi/multipleChoiceQuestionsWithAnswers/question](http://localhost:8080/quizzesapi/swagger-ui/index.html#/multiple-choice-questions-controller/saveMultipleChoiceQuestionAndAnswers)
2. Προβολή ερωταπαντήσεων για επεξεργασία ή απλή προβολή από συντάκτη
   1. Προβολή ερωτήσεων σωστού λάθους και των απαντήσεών
      τους [/quizzesapi/trueFalseQuestionsWithAnswers/question?questionId=1](http://localhost:8080/quizzesapi/swagger-ui/index.html#/true-false-questions-controller/getTrueFalseQuestionAndAnswers)
   2. Προβολή ερωτήσεων συμπλήρωσης
      κενών [/quizzesapi/gapFillingQuestionsWithAnswers/question?questionId=1](http://localhost:8080/quizzesapi/swagger-ui/index.html#/gap-filling-questions-controller/getGapFillingQuestionAndAnswers)
   3. Προβολή ερωτήσεων πολλαπλής επιλογής (1 ή περισσότερες σωστές
      απαντήσεις) [/quizzesapi/multipleChoiceQuestionsWithAnswers/question?questionId=1](http://localhost:8080/quizzesapi/swagger-ui/index.html#/multiple-choice-questions-controller/getMultipleChoiceQuestionAndAnswers)
3. Διαγραφή συγκεκριμένης απάντησης μιας ερώτησης [/quizzesapi/answers?answerId=1](http://localhost:8080/quizzesapi/swagger-ui/index.html#/answer-controller/deleteAnswer)
4. Διαγραφή ερώτησης και των απαντήσεων αν η ερώτηση δεν έχει ήδη συμπεριληφθεί σε quiz [/quizzesapi/questionsWithAnswers?questionId=1](http://localhost:8080/quizzesapi/swagger-ui/index.html#/questions-controller/deleteQuestion)
5. Προβολή λίστας όλων των ερωτήσεων, με προβολή μόνο κάποιων συγκεκριμένων στοιχείων καθεμιάς [/quizzesapi/questionsWithAnswers/index/questions](http://localhost:8080/quizzesapi/swagger-ui/index.html#/questions-controller/getQuestions)
6. Φιλτράρισμα και προβολή μόνο των ερωτήσεων, με αναζήτηση συγκεκριμένων
   tags [/quizzesapi/questionsWithAnswers/search?tagsSeparatedByComma=javasc,comment](http://localhost:8080/quizzesapi/swagger-ui/index.html#/questions-controller/getQuestionsRelevantToTags)

### 2. Καθορισμός ερωτηματολογίου - τεστ με χρήση των ερωτημάτων

1. Προσθήκη ερωτηματολογίου, με καθορισμό μερών και βαθμολογημένων ερωτήσεων, επιλέγοντας ερωτήσεις και ορίζοντας βαθμό
   για κάθε
   απάντηση [/quizzesapi/quizzesWithPartsAndQuestions/quiz/save](http://localhost:8080/quizzesapi/swagger-ui/index.html#/quizzes-controller/saveQuiz)
2. Προβολή ερωτηματολογίων για επεξεργασία ή απλή προβολή από
   συντάκτη [/quizzesapi/quizzesWithPartsAndQuestions/quiz?id=1](http://localhost:8080/quizzesapi/swagger-ui/index.html#/quizzes-controller/getViewQuiz)
3. Προβολή λίστας όλων των ερωτηματολογίων, με προβολή μόνο κάποιων συγκεκριμένων στοιχείων
   καθενός [/quizzesapi/quizzesWithPartsAndQuestions/index/quizzes](http://localhost:8080/quizzesapi/swagger-ui/index.html#/quizzes-controller/getQuizMiniDtos)
4. Διαγραφή quiz αν το quiz είναι
   κενό [/quizzesapi/quizzesWithPartsAndQuestions?quizId=1](http://localhost:8080/quizzesapi/swagger-ui/index.html#/quizzes-controller/deletePart)
5. Διαγραφή μέρους quiz αν αυτό είναι
   κενό [/quizzesapi/quizzesPartsWithQuestions?partId=32](http://localhost:8080/quizzesapi/swagger-ui/index.html#/quizzes-parts-controller/deletePart_1)
6. Διαγραφή βαθμολογημένης ερώτησης με τις βαθμολογημένες απαντήσεις
   της [/quizzesapi/gradedQuestions?gradedQuestionId=343](http://localhost:8080/quizzesapi/swagger-ui/index.html#/graded-questions-controller/deleteGradedQuestion)

### 3. Καθορισμός συμμετεχόντων

1. Προσθήκη συμμετεχόντων [/quizzesapi/participantsData/participant](http://localhost:8080/quizzesapi/swagger-ui/index.html#/participant-controller/saveParticipant)
2. Προβολή συμμετεχόντων για επεξεργασία ή απλή προβολή από συντάκτη [/quizzesapi/participantsData/participant?id=1](http://localhost:8080/quizzesapi/swagger-ui/index.html#/participant-controller/getViewParticipant)
3. Προβολή λίστας όλων των συμμετεχόντων [/quizzesapi/participantsData/index/participants](http://localhost:8080/quizzesapi/swagger-ui/index.html#/participant-controller/getAllParticipants)
4. Διαγραφή συμμετέχοντα [/quizzesapi/participantsData/participant?id=1](http://localhost:8080/quizzesapi/swagger-ui/index.html#/participant-controller/deleteParticipant)
   <br/><hr/>
5. Προσθήκη εξετάσεων [/quizzesapi/examsWithParticipantsAndQuiz/exam](http://localhost:8080/quizzesapi/swagger-ui/index.html#/exams-controller/saveExam)
6. Προβολή εξετάσεων για επεξεργασία ή απλή προβολή από συντάκτη [/quizzesapi/examsWithParticipantsAndQuiz/exam?id=1](http://localhost:8080/quizzesapi/swagger-ui/index.html#/exams-controller/getViewExam)
7. Προβολή λίστας όλων των εξετάσεων [/quizzesapi/examsWithParticipantsAndQuiz/index/exams](http://localhost:8080/quizzesapi/swagger-ui/index.html#/exams-controller/getExamMiniDtos)
8. Προβολή εξέτασης για συμπλήρωση από έναν συμμετέχοντα,
   (προκειμένου να μπορούν να καταχωρηθούν απαντήσεις του συμμετέχοντα για συγκεκριμένες βαθμολογημένες ερωτήσεις,
   σε συγκεκριμένη εξέταση) [/quizzesapi/examsWithParticipantsAndQuiz/examForCompletion?id=1](http://localhost:8080/quizzesapi/swagger-ui/index.html#/exams-controller/getExamForCompletion)
   <br/><hr/>
9. Ενημέρωση εξεταζόμενων για μια εξέταση που θα δώσουν σε συγκεκριμένη ημερομηνία, με email [/quizzesapi/examsWithParticipantsAndQuiz/notifyParticipants?examId=1&specificDate=2023-12-22](http://localhost:8080/quizzesapi/swagger-ui/index.html#/exams-controller/notifyParticipants)

### 4. Διεξαγωγή τεστ και έλεγχος αποτελεσμάτων

1. Καταχώρηση απαντήσεων (Responses)
    1. Καταχώρηση απαντήσεων σε ερωτήσεις σωστού λάθους [/quizzesapi/true-false-response/response/save](http://localhost:8080/quizzesapi/swagger-ui/index.html#/true-false-response-controller/saveResponse)
    2. Καταχώρηση απαντήσεων σε ερωτήσεις συμπλήρωσης κενών [/quizzesapi/gap-filling-response/response/save](http://localhost:8080/quizzesapi/swagger-ui/index.html#/gap-filling-response-controller/saveResponse_2)
    3. Καταχώρηση απαντήσεων σε ερωτήσεις πολλαπλής επιλογής [/quizzesapi/multiple-choice-response/response/save](http://localhost:8080/quizzesapi/swagger-ui/index.html#/multiple-choice-response-controller/saveResponse_1)
2. Υπολογισμός score για συγκεκριμένη
   απάντηση [/quizzesapi/responses/score?responseId=2](http://localhost:8080/quizzesapi/swagger-ui/index.html#/response-controller/getScore)
3. Υπολογισμός score για συμμετέχοντα σε συγκεκριμένη
   εξέταση [/quizzesapi/examParticipant/getParticipantScoreToExam](http://localhost:8080/quizzesapi/swagger-ui/index.html#/exam-participant-controller/getParticipantScoreToExamStr)

## Testing

Για τις περισσότερες από τις παραπάνω δυνατότητες υπάρχουν springBootTests που μπορούν να δοκιμαστούν με h2 database

## Άλλες επισημάνσεις

### Front end

Μπορείτε να δείτε στον παρακάτω σύνδεσμο, [figma.com/file/quizzes](https://www.figma.com/file/9UvDCPIKHjJUKNmed41IqX/quizzes), 
ένα πρόχειρο πιθανό design του front-end, πίσω από τη λογική της αρχιτεκτονικής του API 
και των διαθέσιμων endpoints.

### Development server

Η εφαρμογή [επανεκκινείται](https://www.baeldung.com/spring-boot-devtools) αυτόματα 
εάν κάνετε οποιαδήποτε αλλαγή στον πηγαίο κώδικα.

### Database

Μπορείτε να εκτελέσετε την εφαρμογή με τη βάση δεδομένων [h2](https://www.baeldung.com/spring-boot-h2-database) επίσης.
