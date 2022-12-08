# 나만의 항해 블로그 백엔드 서버 만들기

## 목차
[1. 프로젝트 기간](#프로젝트-기간) <br>
[2. API 명세서](#api-명세서) <br>
[3. 프로젝트 회고](#프로젝트-회고) <br>

<br><br>

## 프로젝트 기간
 - 2022년 12월 5일 ~ 2022년 12월 9일

<br><br>

## API 명세서
|기능| METHOD | URL | Request | Response |
|---|---|:---|:---|:---|
|글 작성하기| `Post` | /api/posts | {<br>"subject": "subject",<br>"username": "username",<br> "contents": "contents",<br> "password": "password"<br>} |  {<br>"createAt": "createAt",<br>"id": "id",<br>"subject": "subject",<br>"username": "username",<br>"contents": "contents"<br>}|
|글 목록 조회하기| `Get` | /api/posts | None | {<br>{<br>"createAt": "createAt",<br>"id": "id",<br>"subject": "subject",<br>"username": "username",<br>"contents": "contents"<br>},<br>...<br>}|
|글 조회하기| `Get` | /api/posts/{id} | None |{<br>"createAt": "createAt",<br>"id": "id",<br>"subject": "subject",<br>"username": "username",<br>"contents": "contents"<br>} |
|글 수정하기| `Put` | /api/posts/{id} | {<br>"subject": "subject",<br>"username": "username",<br> "contents":"contents",<br> "password": "password"<br>} | {<br>"createAt": "createAt",<br>"id": "id",<br>"subject": "subject",<br>"username": "username",<br>"contents": "contents"<br>} |
|글 삭제하기| `Delete` | /api/posts/{id} | {"password":"password"} | {"success": true or false} |

<br><br>

## 프로젝트 회고
1. 프로젝트 경험<br>
 이번 프로젝트에서 느끼는 것이 생각보다 크다. 특히 이번에는 직접 백엔드 서버를 작성하면서 처음 사용해본 문법이라던지, 새로운 프레임 워크에 대한 적응이 필요했고, 이번 프로젝트에서 그러한 적응이 충분히 되었다고 생각한다. 스프링 입문 주차에서 생각보다 많은 경험을 얻을 수 있었던 프로젝트가 아니였나 싶다. 

2. 수정과 삭제 API의 Request?<br>
 해당 프로젝트에서는 Body타입을 사용해서 얻었다. 특히 두가지 api에서 요구하는 내용이 달랐는데, id의 경우 해당 글이 페이지별로 작성되어있다고 가정했을 때, 페이지에 각각의 id를 부여하고 그 id를 key로 사용하여 해당 글의 정보가 담긴 DB에서 정보를 가져왔다. <br>
 수정 API의 경우에는, Request로 사용되는 DTO의 정보가 총 4가지로 블로그 작성시와 같은 정보가 담긴 Json 형식의 Request를 요구했고, 삭제 API의 경우에는 해당글의 Password를 Json형식으로 변경하고 Request를 요구하는 식으로 작성했다.

3. RESTful API? <br>
 필자는 아직도 Restful 하다는 표현이 잘 와닿지는 않는 표현이다. 나의 지식에서는 충분히 잘 만들어진 API라고 생각하지만, 아는 지식이 크게 없으니 명확한 판단을 할 수 없다고 생각한다. 

4. 관심사 분리 <br>
 해당 프로그램에서는 최대한 각각의 역할을 분담하려고 노력했다. Url에 직접적으로 요청을 받아들일 수 있는 부분(Controller)과, 해당 요청을 수행하는 부분(Service)을 나누었고, 요청을 수행하기 위해 필요한 것들을 최대한 나누었다고 생각한다.

5. 과제와 별도로 추가된 기능 <br>
 과제를 수행하면서 생각했던 것 중, password 정보에 관해서 생각을 해봤는데, 해당 정보는 제3자에게 공개되어서는 안된다고 생각했고, 정보의 암호화가 필요하다고 생각했다. 그래서 spring에서 제공해주는 security를 활용해 password 정보를 단방향 암호화를 했으며, 해당 정보를 비교해야 하는 경우 활용할 수 있는 메소드를 작성했다. 또한 요청된 Post 정보에 Password를 포함시키지 않게 적용시켰다.