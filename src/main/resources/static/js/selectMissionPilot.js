function selectBattle(event) {
    const pilots = document.getElementById("pilot").options;

    for (let i = 0; i < pilots.length; i++) {
      const pilot = pilots[i];
        if (pilot.getAttribute("rank") === "Apprentice") {
            pilot.style.display = "none";
        } else {
            pilot.style.display = "";
        }
    }
}
function selectTraining(event) {
    const pilots = document.getElementById("pilot").options;

    for (let i = 0; i < pilots.length; i++) {
        const pilot = pilots[i];
        pilot.style.display = "";
    }
}