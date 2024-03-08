# Coding Guideline

The coding guideline for this project are listed in this file.
Anyone can create a PR to update this coding guideline.
Code from a new PR should follow this coding guideline.

### Common naming rule for attribute

- Camel case should be used for attribute name.
- Acronym should also follow the camel case rule.
- Avoid using acronym as possible as we can.

#### DO
- resultVisible
- userId
- adminMultiFactorAuthentication

###
- userID
- adminMfaAuthentication
- adminMFAAuthentication

### Rule for date and time

- For the response entities and the database objects, timestamp since `1970/01/01 00:00:00` should be used, the type of the timestamp should be Long. The suffix should be `at`, for example, `expireAt`
- For the request entities, string with format like `yyyy-MM-dd` should be used. The suffix should be `date`, for example, `expireDate`

### Naming rule for boolean attribute

Adjective or past participle should be used as the name of any boolean attribute.
Please attention that `is` cannot be used as prefix.

#### DO
- resultVisible
- used
- deleted

#### DO NOT
- showResult
- isDeleted

### Rule for API path

Use hyphen instead of underscores or CamelCase

eg.

#### DO
- `/v1/admin/examiner-manager`

#### DO NOT
- `/v1/admin/examiner_manager`
- `/v1/admin/examinerManager`

### Define a function

For a function name within 128 characters, a single line is acceptable.

```kotlin
fun answerQuestion(examineeId: Long, questionAnswerId: Long): NextQuestionEntity {
    
}
```

For other long function name, multiple lines is required as the following style
- A parameter use a single line, and should be end with a comma `,`
- The returned value use a single line.

```kotlin
fun nextQuestion(
    examineeAnswers: List<ExamineeAnswerEntity>,
    questionWithAttributes: List<QuestionWithAttributesEntity>,
): Long {
    TODO()
}
```

### Request function

The order of annotations in a request function should be as the following
- Mapping annotation like `@PostMapping`
- Permission annotation `@Permitted`
- Swagger document annotation `@Operation`

The order of parameters in a request function should be as the following
- `@PathVariable`
- `@RequestBody`
- `@AuthenticationPrincipal`

The sample code:

```kotlin
@PostMapping("/v1/exam/result/{examineeId}/resend_reminder_email")
@Permitted(AdminPermission.EXAM_RESULT)
@Operation(summary = "Resend reminder email to examinee")
fun resendReminderEmail(
    @PathVariable examineeId: Long,
    @RequestBody @Valid request: ExamResultResendEmailRequest,
    @AuthenticationPrincipal jwtUser: AdminJwtUser,
): EmptyResponse {
    TODO()
}
```

### Avoid nullable

Better to avoid optional variable in code and nullable column in DB.

For example, if the gender attribute for a user is optional, better to add an `UNKOWN` item for `Gender` enum class, rather than making `Gender` optional.
### DO
```kotlin
enum class Gender {
    MALE,
    FEMALE,
    ;
}
```

### DO NOT
```kotlin
enum class Gender {
    MALE,
    FEMALE,
    UNKNOWN,
    ;
}
```
