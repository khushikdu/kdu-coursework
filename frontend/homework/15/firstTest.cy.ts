describe("cypress demo", () => {
  it("performs unit testing", () => {
    cy.visit("http://localhost:5173/");

    cy.get('[data-testid="header-title"]')
      .should("exist")
      .should("have.text", "Item Lister");

    cy.get("#search").should("exist").focus().type("Hello World");

    cy.get("#search").should("exist").clear();

    cy.get('[data-testid="input-field"]').focus().type("Todo 1");

    cy.get('[data-testid="add-btn"]').click();
    cy.get('[data-testid="delete-btn"]').click();

    cy.get('[data-testid="input-field"]').focus().type("Todo 1");
    cy.get('[data-testid="add-btn"]').click();

    cy.get('[data-testid="input-field"]').focus().type("Todo 2");

    cy.get('[data-testid="add-btn"]').click();
    cy.get('[data-testid="clear-btn"]').click();

  
  });
});
