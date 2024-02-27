describe('cypress demo', () => {
  it('performs unit testing', () => {
    cy.visit('http://localhost:5173/')
    
    cy.get('[data-testid="header-title"]').should("exist")
    .should("have.text","Item Lister" );

    cy.get('[]data-testid="input-field"')
      .focus()
      
    
  });
})