document.addEventListener("DOMContentLoaded", () => {
  console.log("Portfolio page loaded.");

  const cards = document.querySelectorAll(".portfolio-card");

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
});
