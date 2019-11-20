Reviewer: ChienTX / Trainee: ThuanVD
-----

### Jul 19, 2019

+ tên hàm tối nghĩa: `clickCloseA`
+ sao không dùng jQuery ở đây? `document.querySelector("html").classList.add('js');`
+ đã chuyển sang dùng application.scss và import bootstrap thì nên bỏ nguyên khúc require phía dưới đi
+ admin_controller: số 10 nên được đặt thành Constant
+ `where(sharing_mode: true)` -> nên đưa thành scope trong model
+ nên chuyển qua carrierwave để dùng thử cho quen
+ `[first_name, last_name].select(&:present?).join(' ').titleize` nên chuyển thành `[first_name, last_name].compact.join(' ').titleize`
+

### Jul 12, 2019

+ Trong file `application.js` không nên viết code xử lý trong đó, nên tách ra file riêng
+ giữa các hàm nên phân tách nhau bằng 1 dòng trống
+ style không thống nhất khi đặt tên trong file CSS, nên chọn 1 style duy nhất và follow theo (ví dụ đang thấy: btnFollowCard, size-image)
+ trong hàm `showImageAlbum`, prepend chuỗi HTML như vậy vào hơi khó quản lý và update. Nên xem xét chuyển sang dùng dạng template (`<script type='text/template'>`). Tham khảo: https://jonsuh.com/blog/javascript-templating-without-a-library/

+ tận dụng sức mạnh của SCSS để làm gọn code CSS

Ví dụ thay vì:

```
.js .input-file-trigger {

}

.js .input-file{

}
```

Thì có thể ghi thành

```
.js {
  .input-file-trigger {

  }

  .input-file {

  }
}
```

+ sai chính tả? `.login-orm`

+ chưa có phân trang cho albums, photos

+ không để trống dòng cuối cùng trước khi `end` class (`photo.rb`, `relationship.rb`, `user.rb`)

+ gán thế này `<% a = (index*5).to_s %>` mà không thấy dùng ở đâu cả

+ còn inline style trong code HTML khá nhiều (vd: /app/albums/show.html.erb)

+ file layout application nên làm gọn lại, tách các modal, navigation ra partial riêng để dễ theo dõi, quản lý

+ mình không nên gắn sự kiện dùng thuộc tính "onClick" hay "onChange" trên thẻ HTML, thay vì đó mình nên dùng jQuery và gắn sự kiện vào qua hàm "on"

+ Chưa dùng i18n

+ Tránh việc sử dụng biến instance @var ở trong partial

+ Một số đoạn code trong users/show.html.erb thấy có vẻ trùng lặp với albums/show.html.erb

+ Khai báo root trong routes.rb thì nên đặt trên đầu file

+ Thông tin nhạy cảm hoặc thông tin cá nhân nên tránh đưa vào git (ví dụ file database.yml đang có username và password của database)