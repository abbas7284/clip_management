# ----- مرحله اول: ساخت پروژه (Build Stage) -----

# استفاده از یک ایمیج رسمی Maven با JDK 17 به عنوان ایمیج پایه
FROM maven:3.9.6-eclipse-temurin-17 AS build

# کپی کردن تمام کدهای پروژه به داخل ایمیج
COPY . .

# اجرای دستور Maven برای ساخت پروژه و تولید فایل .jar
# این دستور فایل clip_management-0.0.1-SNAPSHOT.jar را در پوشه /target می‌سازد
RUN mvn clean install


# ----- مرحله دوم: ساخت ایمیج نهایی (Package Stage) -----

# استفاده از یک ایمیج سبک‌تر فقط با Java 17 JRE برای اجرای برنامه
FROM eclipse-temurin:17-jre

# کپی کردن فایل .jar ساخته شده از مرحله قبل (build) به ایمیج نهایی
# و تغییر نام آن به app.jar برای سادگی
COPY --from=build /target/clip_management-0.0.1-SNAPSHOT.jar app.jar

# مشخص کردن پورتی که برنامه شما روی آن اجرا می‌شود
# (این دستور به تنهایی کاری انجام نمی‌دهد اما به عنوان مستندات عمل می‌کند)
EXPOSE 8080

# دستور اصلی که هنگام اجرای کانتینر اجرا می‌شود
# این دستور برنامه جاوا را اجرا می‌کند
ENTRYPOINT ["java","-jar","app.jar"]