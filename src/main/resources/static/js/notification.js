let myPopUpNotifications = [];

function getType(type) {
  type = type.toLowerCase();

  switch (type) {
    case "success":
      return "success";
    case "info":
      return "info";
    case "error":
      return "error";
    case "warning":
      return "warning";
    default:
      return "info";
  }
}

function getBgColor(type) {
  type = type.toLowerCase();

  switch (type) {
    case "success":
      return "#16a34a";
    case "info":
      return "#2563eb";
    case "error":
      return "#dc2626";
    case "warning":
      return "#ea580c";
    default:
      return "#2563eb";
  }
}

function getRandomStr(length) {
  const characters = 'abcdefghijklmnopqrstuvwxyz';
  let result = '';

  for (let i = 0; i < length; i++) {
    const randomIndex = Math.floor(Math.random() * characters.length);
    result += characters.charAt(randomIndex);
  }

  return result;
}

// Tạo chuỗi ngẫu nhiên dài 5 ký tự
const classPrefix = getRandomStr(5);

// Init a container
function initContainer() {
  const container = document.createElement('section');
  container.id = classPrefix + "_notification_container";
  container.style.position = "fixed";
  container.style.top = "20px";
  container.style.right = "20px";
  container.style.display = "flex";
  container.style.flexDirection = "column";
  container.style.height = "max-content"
  container.style.minWidth = "200px";
  container.style.transition = "height 0.25s ease";
  container.style.zIndex = "999";
  document.body.appendChild(container);

  // Tạo thẻ <style> mới
  const style = document.createElement('style');
  style.innerHTML = `
  .${classPrefix}_notification {
    display: flex;
    align-items: center;
    gap: 8px;
    position: relative;
    min-width: 200px;
    max-width: 320px;
    padding: 15px;
    color: white;
    border-radius: 5px;
    transition: max-height 0.75s ease, margin-bottom 0.4s ease, opacity 0.2s ease;
    animation: ${classPrefix}_flyin-animation 0.25s ease-in forwards;
    margin-bottom: 12px;
    overflow:hidden;
  }

  .${classPrefix}_notification:hover .${classPrefix}_progress {
      animation-play-state: paused;
  }

  .${classPrefix}_progress {
      background: rgba(255, 255, 255, 0.5);
      height: 5px;
      position: absolute;
      bottom: 0;
      left: 0;
      width: 100%;
  }

  @keyframes ${classPrefix}_progress-animation {
      from {
          width: 100%;
      }

      to {
          width: 0;
      }
  }

  @keyframes ${classPrefix}_flyin-animation {
      0% {
          opacity: 0.5;
          transform: translateX(10px);
      }

      70% {
          opacity: 0.7;
          transform: translateX(-10px);
      }

      100% {
          opacity: 1;
          transform: translateX(0px);
      }
  }
  `;

  // Lấy thẻ <head>
  const head = document.head || document.getElementsByTagName('head')[0];

  // Thêm thẻ <style> vào đầu thẻ <head>
  head.appendChild(style);
}
initContainer();

// {
//   title: String,
//   message: String,
//   type: "success" | "error" | "info" | "warning",
//   url?: String
// }
function showNotification(data, duration = 8000) {
  if (data.message == null) {
    throw new Error("Notification format is invalid!", data);
  }

  // Thiết lập thời gian cơ bản
  const baseTime = duration; // Thoi gian toi thieu
  const timePerCharacter = 50; // 50ms cho mỗi ký tự
  const maxDisplayTime = 120000; // Giới hạn tối đa: 2 phút

  // Tính toán thời gian hiển thị
  duration = Math.min(baseTime + data.message.length * timePerCharacter, maxDisplayTime);

  const type = getType(data.type ? data.type : "info");
  const container = document.getElementById(classPrefix + "_notification_container");
  const notification = document.createElement('div');

  // Title
  let notificationTitle = document.createElement("p");
  if (data.url) {
    notificationTitle = document.createElement('a');
    notificationTitle.href = data.url;
  }
  notificationTitle.textContent = data.title;
  notificationTitle.style.fontSize = "16px";
  notificationTitle.style.fontWeight = "500";

  notification.className = `${classPrefix}_notification`;
  notification.style.backgroundColor = getBgColor(type);
  notification.style.boxShadow = "0 1px 2px 0 rgb(0 0 0 / 0.05)";

  // Icon
  const notifIconImage = document.createElement("img");
  notifIconImage.src = `/images/${type}.png`;
  notifIconImage.style.height = "24px";
  notifIconImage.style.width = "24px";
  notifIconImage.style.display = "block";
  notifIconImage.style.flexShrink = "0";
  notification.appendChild(notifIconImage);
  
  // Message body
  const notificationMessage = document.createElement("p");
  notificationMessage.textContent = data.message;
  notificationMessage.style.fontSize = "12px";
  
  // Notification body: Title + paragraph
  const notificationBody = document.createElement("section");
  notificationBody.appendChild(notificationTitle);
  notificationBody.appendChild(notificationMessage);
  notification.appendChild(notificationBody);
  
  // Notification close button
  const closeButton = document.createElement("button");
  closeButton.style.display = "block";
  closeButton.style.marginLeft = "auto";
  closeButton.style.flexShrink = "0";
  closeButton.innerHTML = `<img src="/svg/close.svg" style="display:block;height=24px;width=24px;" />`
  notification.appendChild(closeButton);


  // Progress bar
  const progress = document.createElement('div');
  progress.className = `${classPrefix}_progress`;
  // Cài đặt thuộc tính animation cho thanh progress
  progress.style.animation = `${classPrefix}_progress-animation ${duration}ms linear forwards`;
  notification.appendChild(progress);

  // Append to container
  container.appendChild(notification);
  myPopUpNotifications.push(notification);

  // Hủy timeout
  const hideNotification = () => {
    notification.style.maxHeight = "0px";
    notification.style.opacity = "0";
    notification.style.pointerEvents = "none";
    notification.style.display = "block";
    notification.style.overflow = "hidden";
    notification.style.lineHeight = "0";
    notification.style.padding = "0px";
    notification.style.marginBottom = "0px";
    // Sau khi hiệu ứng hoàn thành, xóa thông báo
    setTimeout(() => {
      notification.remove();
      myPopUpNotifications.shift(); // Xóa thông báo khỏi mảng
    }, 800);
  };

  const timeoutId = setTimeout(hideNotification, duration);

  // Lắng nghe sự kiện hover
  notification.addEventListener('mouseenter', () => {
    progress.style.animationPlayState = 'paused'; // Dừng animation
    clearTimeout(timeoutId); // Hủy timeout khi hover
  });

  notification.addEventListener('mouseleave', () => {
    progress.style.animationPlayState = 'running'; // Resume animation
    const remainingDuration = duration * ((parseFloat(getComputedStyle(progress).width) / parseFloat(getComputedStyle(notification).width)));
    setTimeout(hideNotification, remainingDuration + 100);
  });

  // Lắng nghe sự kiện click
  closeButton.addEventListener('click', () => {
    progress.style.animationPlayState = 'paused'; // Dừng animation
    clearTimeout(timeoutId); // Hủy timeout khi hover
    hideNotification();
  });
}