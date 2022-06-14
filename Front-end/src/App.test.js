import { render, screen } from '@testing-library/react';
import StudyMaterials from './StudyMaterials';

test('renders learn react link', () => {
  render(<StudyMaterials />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});
