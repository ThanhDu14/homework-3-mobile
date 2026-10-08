# 04 – Tiến độ & checklist nộp bài

**Hôm nay:** Thứ Năm 08/10/2026 · **Deadline nộp:** trước **20:00 Thứ Bảy 10/10/2026**

Thời gian rất ngắn (~2,5 ngày) → mỗi mốc phải đúng giờ. Trễ mốc thì báo nhóm ngay.

## Mốc thời gian

| Thời điểm | Việc | Người |
|-----------|------|-------|
| **Thứ Năm 08/10, 21:00** | Cả nhóm đọc `docs/`, điền tên/MSSV, tạo nhánh của mình | Cả nhóm |
| **Thứ Năm 08/10, 23:00** | Push `Contact`, `ContactRepository`, 15 ảnh avatar | TV1 |
| **Thứ Sáu 09/10, 12:00** | Tạo PR: layout (TV2), adapter (TV3), paginator + test (TV4) | TV2, TV3, TV4 |
| **Thứ Sáu 09/10, 18:00** | Review + merge các PR, viết `MainActivity`, app chạy được trên `main` | TV1 |
| **Thứ Sáu 09/10, 22:00** | Kiểm thử thủ công vòng 1, báo bug | TV4 (mọi người cùng test) |
| **Thứ Bảy 10/10, 12:00** | Sửa xong toàn bộ bug, chốt code (code freeze) | Người phụ trách phần có bug |
| **Thứ Bảy 10/10, 15:00** | Kiểm thử vòng cuối + chụp màn hình demo | TV4 |
| **Thứ Bảy 10/10, 17:00** | Nén `MSSV.zip`, cả nhóm tải về giải nén thử, build thử | TV1 + 1 người kiểm tra |
| **Thứ Bảy 10/10, trước 20:00** | **Nộp lên website môn học** (mục Bài tập 03), gửi ảnh xác nhận vào nhóm | TV1 |

> Mục tiêu thực tế: nộp xong trong khoảng 17:00–18:00 Thứ Bảy, giữ 2 tiếng dự phòng cho sự cố mạng/website.

## Quy trình Git

```bash
git checkout main && git pull
git checkout -b feature/tvX-...      # nhánh của mình
# ... code, commit nhỏ ...
git push -u origin feature/tvX-...
# tạo Pull Request vào main, nhắn TV1 review
```

Trước khi tạo PR: `./gradlew assembleDebug` (và `./gradlew test` với TV4) phải chạy thành công.

## Checklist kiểm thử thủ công (TV4 chủ trì)

- [ ] Mở app: thấy 5 contact đầu, nút **1 mờ**, nút 2 và 3 sáng, TextView hiện `You choose:`.
- [ ] Nhấn nút 2: thấy contact 6–10, nút **2 mờ**, nút 1 và 3 sáng.
- [ ] Nhấn nút 3: thấy contact 11–15, nút **3 mờ**, nút 1 và 2 sáng.
- [ ] Nhấn nút đang mờ: không có gì xảy ra.
- [ ] Nhấn một item: TextView hiện `You choose: <tên>`, item có nền vàng.
- [ ] Chọn item khác: nền vàng chuyển sang item mới, item cũ hết vàng.
- [ ] Chuyển trang rồi quay lại: item đã chọn vẫn vàng; ở trang khác không có item nào bị vàng sai.
- [ ] Mỗi item có đủ ảnh, họ tên, SĐT; có đường phân cách.
- [ ] Không crash khi xoay màn hình.
- [ ] `./gradlew test` pass.

## Checklist nộp bài (TV1)

- [ ] `main` đã merge đủ 4 phần, build sạch: `./gradlew clean assembleDebug`.
- [ ] Xoá thư mục `build/`, `app/build/`, `.gradle/` trước khi nén (giảm dung lượng; `local.properties` không cần nộp).
- [ ] Nén **toàn bộ mã nguồn** project thành 1 file `MSSV.zip` (MSSV theo quy định cho bài nhóm).
- [ ] Giải nén thử ở thư mục khác, mở bằng Android Studio, chạy được.
- [ ] Nộp đúng mục **Bài tập 03** trên website môn học, trước 20:00 Thứ Bảy 10/10/2026.
- [ ] Gửi ảnh chụp xác nhận đã nộp vào nhóm chat.
