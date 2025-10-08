document.addEventListener("DOMContentLoaded", () => {
  console.log("Certifications page loaded.");

  // Fade-in animation for certificates
  const cards = document.querySelectorAll(".cert-card");

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
