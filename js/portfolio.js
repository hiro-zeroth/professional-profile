document.addEventListener("DOMContentLoaded", () => {
  console.log("Portfolio page loaded.");

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

  document.addEventListener("mousemove", (e) => {
    const x = e.clientX / window.innerWidth;
    const y = e.clientY / window.innerHeight;
    const color1 = `rgba(${100 + x * 155}, ${50 + y * 205}, ${200 - y * 80}, 0.6)`;
    const color2 = `rgba(${255 - x * 155}, ${100 + y * 105}, ${150 + x * 80}, 0.6)`;
    bg.style.background = `radial-gradient(circle at ${x * 100}% ${y * 100}%, ${color1}, ${color2}, #0b0c10)`;
  });
});
