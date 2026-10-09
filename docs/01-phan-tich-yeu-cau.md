# 01 – Phân tích yêu cầu (HW03.pdf)

**Môn:** Phát triển phần mềm cho thiết bị di động
**Bài:** Bài tập 03 – Tùy biến ListView (bài tập nhóm, bài tập cần nộp)

## Giao diện cần làm

```
┌─────────────────────────────────┐
│ You choose: Phan Văn C          │  ← TextView
├─────────────────────────────────┤
│ [ảnh]  Nguyễn Văn A             │
│        0989897873               │
│ ─────────────────────────────── │
│ [ảnh]  Lê Thị B                 │
│        0967995843               │
│ ─────────────────────────────── │  ← ListView (custom item:
│ [ảnh]  Trần Văn C               │     ảnh + họ tên + SĐT)
│        0907955843               │
│ ─────────────────────────────── │
│▓[ảnh]  Phan Văn C              ▓│  ← item đang chọn: nền vàng
│▓       0967885811              ▓│
│ ─────────────────────────────── │
│ [ảnh]  Đinh Văn D               │
│        0988885231               │
├─────────────────────────────────┤
│                     [1] [2] [3] │  ← 3 nút phân trang
└─────────────────────────────────┘
```

## Yêu cầu chức năng

| # | Thành phần | Yêu cầu |
|---|-----------|---------|
| R1 | **TextView** | Khi người dùng chọn một item, TextView hiển thị tên tương ứng (`You choose: <tên>`). |
| R2 | **ListView** | Liệt kê các item gồm: **hình ảnh, họ tên, số điện thoại** (phải tùy biến item, không dùng `simple_list_item`). |
| R3 | **Nút 1, 2, 3** | Mặc định nút 1 bị **mờ** (đang chọn), nút 2 và 3 sáng. Nhấn nút 2 → hiển thị 5 dòng kế tiếp, nút 2 mờ, nút 1 và 3 sáng. Tương tự nút 3. |
| R4 | **Dữ liệu** | Tổng 5 item × 3 nút = **15 dòng**, mỗi trang 5 dòng. |
| R5 | (theo hình) | Item đang chọn được tô nền vàng; có đường kẻ phân cách giữa các item. |

## Quy định nộp bài

- Nộp **toàn bộ mã nguồn** chương trình (ví dụ thư mục `CustomListDemo` → của nhóm là thư mục project `Homework3`).
- Nén thành **1 file duy nhất**, đặt tên `MSSV.zip` (hoặc `MSSV.rar`).
  - Bài nhóm: thống nhất dùng MSSV của nhóm trưởng (hoặc theo quy định giảng viên thông báo trên lớp — TV1 xác nhận lại).
- Nộp trên website môn học, mục **Bài tập 03**.
- Bài giống nhau giữa các nhóm/sinh viên **không có điểm** → dữ liệu (tên, SĐT, ảnh) và giao diện nên tự làm, không chép.

## Tiêu chí "xong" (Definition of Done)

- [ ] App build và chạy được trên emulator/điện thoại (minSdk 29).
- [ ] Mở app: trang 1 hiển thị 5 contact đầu, nút 1 mờ, nút 2–3 sáng.
- [ ] Nhấn nút 2 / 3 → đổi sang đúng 5 contact tương ứng, trạng thái nút đổi đúng.
- [ ] Nhấn một item → TextView hiển thị đúng tên, item được tô nền vàng.
- [ ] Mỗi item có ảnh, họ tên, SĐT; có đường phân cách.
- [ ] Đủ 15 contact, không trùng.
- [ ] Unit test phân trang chạy pass (`./gradlew test`).
- [ ] Đã xoá thư mục `build/`, `.gradle/` trước khi nén; tên file nén đúng `MSSV.zip`.
