import { render, screen, fireEvent } from '@testing-library/react';
import { vi } from 'vitest';
import BackArrowButton from './BackArrowButton';

const navigate = vi.fn();
vi.mock('react-router-dom', () => ({
  useNavigate: () => navigate
}));

test('calls navigate when button clicked', () => {
  render(<BackArrowButton />);
  fireEvent.click(screen.getByRole('button'));
  expect(navigate).toHaveBeenCalledWith(-1);
});
