document.addEventListener("DOMContentLoaded", () => {
  console.log("Advocacy page loaded.");

  const cards = document.querySelectorAll(".card");

  const revealOnScroll = () => {
    const triggerBottom = window.innerHeight * 0.85;

    cards.forEach(card => {
      const top = card.getBoundingClientRect().top;
      if (top < triggerBottom) card.classList.add("show");
    });
  };

  window.addEventListener("scroll", revealOnScroll);
  revealOnScroll();
});
