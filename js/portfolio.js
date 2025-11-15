document.addEventListener("DOMContentLoaded", () => {
  console.log("Portfolio page loaded.");

  // ===== Card Reveal Animation =====
  const cards = document.querySelectorAll(".project-card");
  const bg = document.getElementById("interactive-bg");

  const revealOnScroll = () => {
    const triggerBottom = window.innerHeight * 0.85;
    cards.forEach(card => {
      const cardTop = card.getBoundingClientRect().top;
      if (cardTop < triggerBottom) {
        card.classList.add("show");
      }
    });
  };

  window.addEventListener("scroll", revealOnScroll);
  revealOnScroll();

  // ===== Interactive Background =====
  document.addEventListener("mousemove", (e) => {
    const x = e.clientX / window.innerWidth;
    const y = e.clientY / window.innerHeight;
    const color1 = `rgba(${100 + x * 155}, ${50 + y * 205}, ${200 - y * 80}, 0.6)`;
    const color2 = `rgba(${255 - x * 155}, ${100 + y * 105}, ${150 + x * 80}, 0.6)`;
    bg.style.background = `radial-gradient(circle at ${x * 100}% ${y * 100}%, ${color1}, ${color2}, #0b0c10)`;
  });

  // ===================================
  // 🌟 ADD YOUR CAROUSEL CODE HERE
  // ===================================

  const slide = document.querySelector('.carousel-slide');
  const images = document.querySelectorAll('.carousel-slide img');

  if (slide && images.length > 0) {  
    let counter = 0;

    document.querySelector('.next')?.addEventListener('click', () => {
      counter++;
      if (counter >= images.length) counter = 0;
      slide.style.transform = `translateX(${-counter * 100}%)`;
    });

    document.querySelector('.prev')?.addEventListener('click', () => {
      counter--;
      if (counter < 0) counter = images.length - 1;
      slide.style.transform = `translateX(${-counter * 100}%)`;
    });
  }

});
