# 03 – Phân công công việc

| Mã | Họ tên | MSSV | Vai trò |
|----|--------|------|---------|
| TV1 | _(điền)_ | _(điền)_ | Nhóm trưởng – Dữ liệu & tích hợp |
| TV2 | _(điền)_ | _(điền)_ | Giao diện XML |
| TV3 | _(điền)_ | _(điền)_ | Custom Adapter |
| TV4 | _(điền)_ | _(điền)_ | Phân trang & kiểm thử |

Tất cả phải tuân theo [02-quy-uoc-ky-thuat.md](02-quy-uoc-ky-thuat.md). Mốc thời gian chi tiết ở [04-tien-do.md](04-tien-do.md).

---

## TV1 – Nhóm trưởng: Dữ liệu & tích hợp

**Nhánh:** `feature/tv1-data` · **Hạn phần riêng:** Thứ Năm 08/10, 23:00 (để các bạn khác dùng được dữ liệu)

- [ ] Tạo `model/Contact.java` đúng API trong quy ước.
- [ ] Tạo `data/ContactRepository.java` với **15 contact** (họ tên tiếng Việt + SĐT 10 số, tự đặt, không chép nhóm khác).
- [ ] Chuẩn bị **15 ảnh avatar** `avatar_01` … `avatar_15` trong `res/drawable/` (ảnh nhỏ ≤ 50KB/ảnh, hoặc vector).
- [ ] Viết `MainActivity.java`:
  - Lấy dữ liệu từ `ContactRepository`, tạo `Paginator` và `ContactAdapter`, gắn vào `lvContacts`.
  - Gắn sự kiện nút 1/2/3 → `paginator.goToPage(i)` → `adapter.setItems(...)` → cập nhật trạng thái nút.
  - Gắn `setOnItemClickListener` → cập nhật `tvSelected` + `adapter.setSelectedContact(...)`.
  - Giữ code window insets hiện có.
- [ ] Review và merge PR của TV2, TV3, TV4 vào `main`.
- [ ] Xác nhận với giảng viên cách đặt tên file nộp cho bài nhóm (MSSV ai).
- [ ] **Nén và nộp bài** (`MSSV.zip`) lên website môn học.

**Bàn giao:** project build được trên `main`, file zip đã nộp, chụp màn hình xác nhận nộp gửi vào nhóm.

---

## TV2 – Giao diện XML

**Nhánh:** `feature/tv2-layout` · **Hạn phần riêng:** Thứ Sáu 09/10, 12:00

- [ ] Viết lại `activity_main.xml` (giữ `android:id="@+id/main"` ở root):
  - `tvSelected` ở trên cùng.
  - `lvContacts` chiếm phần giữa (`layout_height="0dp"` + constraint / weight).
  - Hàng 3 nút `btnPage1..3` ở góc dưới phải như hình (nút vuông nhỏ).
  - Divider giữa các item (`android:divider`, `android:dividerHeight`).
- [ ] Tạo `item_contact.xml`: `imgAvatar` bên trái, `tvName` + `tvPhone` xếp dọc bên phải.
- [ ] Tạo `colors.xml` (`item_selected`, `divider`), `strings.xml` (`you_choose`, `you_choose_none`), `dimens.xml` nếu cần.
- [ ] Tạo `drawable/bg_item_selected.xml`.
- [ ] Kiểm tra layout trên màn hình nhỏ (5") và lớn (6.7"), không bị cắt nút.
- [ ] Dùng `tools:` attributes để xem trước trong Layout Editor (không ảnh hưởng khi chạy).

**Bàn giao:** PR chỉ chứa file trong `res/`, kèm ảnh chụp Layout Editor.

---

## TV3 – Custom Adapter

**Nhánh:** `feature/tv3-adapter` · **Hạn phần riêng:** Thứ Sáu 09/10, 12:00

- [ ] Tạo `adapter/ContactAdapter.java` kế thừa `BaseAdapter`, đúng API trong quy ước.
- [ ] Cài đặt `getView` dùng **ViewHolder pattern** + tái sử dụng `convertView` (giải thích được khi giảng viên hỏi).
- [ ] Bind `imgAvatar`, `tvName`, `tvPhone` từ `Contact`.
- [ ] Highlight: item trùng `selectedContact` → nền `@color/item_selected`, ngược lại nền trong suốt (phải reset nền, tránh lỗi item tái sử dụng bị vàng sai).
- [ ] `setItems()` và `setSelectedContact()` gọi `notifyDataSetChanged()`.
- [ ] Trong lúc chờ TV1/TV2: có thể tạm dùng dữ liệu giả và layout tạm, **không commit** file của người khác.

**Bàn giao:** PR chỉ chứa `ContactAdapter.java`, mô tả ngắn cách hoạt động của ViewHolder (dùng cho phần báo cáo/vấn đáp).

---

## TV4 – Phân trang & kiểm thử

**Nhánh:** `feature/tv4-pagination` · **Hạn phần riêng:** Thứ Sáu 09/10, 12:00 (code) · Thứ Bảy 10/10, 12:00 (kiểm thử)

- [ ] Tạo `pagination/Paginator.java` (Java thuần, generic), đúng API trong quy ước.
- [ ] Viết `PaginatorTest.java` (JUnit 4) tối thiểu các case:
  - 15 item → `getPageCount() == 3`.
  - `getPage(0)` = item 0–4, `getPage(1)` = item 5–9, `getPage(2)` = item 10–14.
  - Mặc định `getCurrentPage() == 0`.
  - `goToPage(-1)` và `goToPage(3)` ném `IllegalArgumentException`.
  - (thêm) 12 item → trang cuối có 2 phần tử.
- [ ] Viết hàm tiện ích (hoặc gợi ý code cho TV1) cập nhật trạng thái 3 nút theo quy ước "mờ".
- [ ] Sau khi TV1 tích hợp: **kiểm thử thủ công** theo checklist trong [01-phan-tich-yeu-cau.md](01-phan-tich-yeu-cau.md) và [04-tien-do.md](04-tien-do.md), ghi bug vào nhóm chat hoặc GitHub Issues.
- [ ] Chụp màn hình 3 trang + trạng thái đã chọn item, lưu vào `docs/screenshots/`.

**Bàn giao:** PR chứa `Paginator.java` + `PaginatorTest.java` (test pass), sau đó là danh sách bug / xác nhận "đạt".

---

## Phụ thuộc giữa các phần

```
TV1 (Contact, data, ảnh) ──┬──► TV3 (Adapter) ──┐
TV2 (layout, ID, màu) ─────┘                    ├──► TV1 (MainActivity, tích hợp) ──► TV4 (kiểm thử) ──► TV1 (nộp)
TV4 (Paginator) ────────────────────────────────┘
```

Nhờ đã chốt API và ID trong file quy ước, TV2, TV3, TV4 có thể bắt đầu ngay mà không cần chờ nhau.
