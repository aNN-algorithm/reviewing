![header](https://capsule-render.vercel.app/api?type=rounded&color=auto&height=100&section=header&text=Reviewing&fontSize=70)
**리뷰 시스템 백엔드 파트 구현**   
: 상품에 대한 review를 작성하고, 상품별 review 점수, 개수, 그리고 리뷰 내용을 관리

# ✔️ 요구사항
- 리뷰 작성 시 검증
- 리뷰 조회는 최신순
- 조회 시 빠른 성능 기대
- Product 테이블과 Review 테이블의 상호작용
- 동시성 제어
- 테스트 코드 작성

# ✔️ 설계 및 구현사항
## 🎯 리뷰 작성
### ✅ 요청
`POST  /products/{productId}/reviews`
```
{
  "userId": 1,
  "score": 4,
  "content": "이걸 사용하고 제 인생이 달라졌습니다.",
}
```
### ✅ 응답
```
NONE
```
### ✅ 처리 과정
## 1. 리뷰는 존재하는 상품에만 작성
> 애플리케이션 로직 내 처리   
> 데이터베이스 내 처리
> 
> <img width="400" alt="image" src="https://github.com/user-attachments/assets/e4365218-2c4d-4084-96ad-03e84f5f12d4">
- 외래키 + 애플리케이션 로직을 같이 처리   
-> 로직 내에서의 예외처리 분만 아니라 DB의 직접적인 데이터 조작 방지
## 2. 유저는 하나의 상품에 대해 하나의 리뷰만 작성
> 애플리케이션 로직 내 처리   
> 데이터베이스 내 처리
>
> ![image](https://github.com/user-attachments/assets/8700c9cd-881a-4ca0-91d2-d0bd9a441f8b)
- 복합 유니크 인덱스를 사용하여, 사용자가 전송 버튼을 연속으로 두 번 이상 눌렀을 때 데이터의 값이 테이블에 중복되는 상황 방지
-> (확장성 고려) 리뷰를 `soft-delete` 로 지우면, 해당 테이블에 같은 페어가 두 개 이상 남지 않도록, 삭제 테이블 구성 고려
## 3. 유저는 1 ~ 5점 사이 점수 부여
> 애플리케이션 로직 내 처리
## 4. 사진은 선택적으로 업로드
- 추후 구현 예정
## 5. 저장
> Review 테이블에 저장
> Product 테이블 내 상품에 점수와 리뷰 개수 반영
- 한 사람이 저장 되기 전 요청을 여러 번 보내는 상황 방지 : 2번 내 작성
- Product 테이블 업데이트는 직접 업데이트 쿼리를 날려 DB lock을 걸어 동시성 제어
## 🎯 리뷰 조회
### ✅ 요청
`POST  /products/{productId}/reviews`
### ✅ 응답
```
{
	"totalCount": 15, // 해당 상품에 작성된 총리뷰 수
	"score": 4.6, // 평균 점수
	"cursor": 6,
	"reviews": [
		{
			"id": 15,
			"userId": 1, // 작성자 유저 아이디
			"score": 5,
			"content": "이걸 사용하고 제 인생이 달라졌습니다.",
			"imageUrl": "/image.png",
			"createdAt": "2024-11-25T00:00:00.000Z"
		},
		{
			"id": 14,
			"userId": 3, // 작성자 유저 아이디
			"score": 5,
			"content": "이걸 사용하고 제 인생이 달라졌습니다.",
			"imageUrl": null,
			"createdAt": "2024-11-24T00:00:00.000Z"
		},...
	]
}
```
### ✅ 처리 과정
### 1. 최근에 작성된 리뷰 순서대로 조회
> 작성일시를 시스템에서 부여하기 때문에 id 내림차순 조회 시 작성된 리뷰 조회 가능
> `cursor based pagination`을 사용하여 일정 개수만큼 조회
- `cursor` 값 0이나 데이터가 없을 시 예외처리 추후 구현 예정
### 2. 인덱스 부여하여 조회 성능 향상
![image](https://github.com/user-attachments/assets/8700c9cd-881a-4ca0-91d2-d0bd9a441f8b)
## 🎯 ERD 명세서
<img width="445" alt="image" src="https://github.com/user-attachments/assets/7a02069b-8bb0-49d3-83a7-4c171f23f416">


## 기술 스택
<div align=left> 
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
</div>
