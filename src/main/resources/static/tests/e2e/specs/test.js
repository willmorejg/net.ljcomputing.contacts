// https://docs.cypress.io/api/table-of-contents

describe('My First Test', () => {
  it('Visits the app root url', () => {
    cy.visit('/').get('a[href*="/view/contacts"]').click()
    expect(true).to.equal(true)
  })
})