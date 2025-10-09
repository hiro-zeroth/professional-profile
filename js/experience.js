document.addEventListener("DOMContentLoaded", () => {
  console.log("Experience page loaded successfully.");

  // Simple animation for experience cards
  const cards = document.querySelectorAll(".exp-card");
  cards.forEach((card, index) => {
    card.style.opacity = "0";
    card.style.transform = "translateY(20px)";
    setTimeout(() => {
      card.style.transition = "all 0.6s ease";
      card.style.opacity = "1";
      card.style.transform = "translateY(0)";
    }, 200 * index);
  });

  // Gradient background logic
  const bg = document.getElementById("interactive-bg");

  // Interactive colorful gradient
  document.addEventListener("mousemove", (e) => {
    const x = e.clientX / window.innerWidth;
    const y = e.clientY / window.innerHeight;
    const color1 = `rgba(${100 + x * 155}, ${50 + y * 205}, ${200 - y * 80}, 0.6)`;
    const color2 = `rgba(${255 - x * 155}, ${100 + y * 105}, ${150 + x * 80}, 0.6)`;

    bg.style.background = `radial-gradient(circle at ${x * 100}% ${y * 100}%, ${color1}, ${color2}, #0b0c10)`;
  });

});
