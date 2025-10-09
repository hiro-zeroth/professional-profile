document.addEventListener("DOMContentLoaded", () => {
  console.log("Advocacy page loaded.");

  const cards = document.querySelectorAll(".card");
  const bg = document.getElementById("interactive-bg");

  // Reveal cards on scroll
  const revealOnScroll = () => {
    const triggerBottom = window.innerHeight * 0.85;
    cards.forEach(card => {
      const top = card.getBoundingClientRect().top;
      if (top < triggerBottom) card.classList.add("show");
    });
  };

  window.addEventListener("scroll", revealOnScroll);
  revealOnScroll();

  // Interactive colorful gradient
  document.addEventListener("mousemove", (e) => {
    const x = e.clientX / window.innerWidth;
    const y = e.clientY / window.innerHeight;

    const color1 = `rgba(${120 + x * 100}, ${60 + y * 150}, ${255 - y * 80}, 0.5)`;
    const color2 = `rgba(${255 - x * 120}, ${120 + y * 100}, ${180 + x * 50}, 0.4)`;

    bg.style.background = `radial-gradient(circle at ${x * 100}% ${y * 100}%, ${color1}, ${color2}, #0b0c10)`;
  });
});
